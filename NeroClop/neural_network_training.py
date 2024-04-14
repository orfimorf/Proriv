import os

from PIL import Image
import numpy as np
import matplotlib.pyplot as plt
from keras.datasets import mnist
import keras
from keras.layers import Dense, Flatten, Reshape, Input
from keras.src.layers import Conv2D, MaxPooling2D, Conv2DTranspose

directory = 'D:/Pyton/Proriv/NeroClop/data_for_incoder_32'


def progress_bar(total, actual):
    l = "Progress: ["

    percent = round(actual * 100 / total, 2)
    painted = int(percent // 10)
    rest = int(10 - painted)

    l += "#" * painted
    l += "." * rest
    l += f"] - {percent}%"

    print('\r' + l, end='')


# Создание списка для хранения массивов изображений

x_train = []

i = 0

# Считывание всех файлов в указанной директории
for filename in os.listdir(directory):
    if filename.endswith(".jpeg") or filename.endswith(".png"):
        # Открытие изображения с помощью Pillow
        img = Image.open(os.path.join(directory, filename))

        # Преобразование изображения в массив и добавление его в список
        img_array = np.array(img)
        x_train.append(img_array)

    i += 1

    progress_bar(20_000, i)

    # if i >= 5000:
    #     break

# Преобразование списка в массив NumPy
x_train = np.array(x_train)

plt.imshow(x_train[100])
plt.show()

x_train = x_train / 255

input_img = Input(shape=(32, 32, 3))
x = Conv2D(filters=32, kernel_size=(4, 4), activation='relu', padding='same')(input_img)
x = MaxPooling2D(pool_size=(2, 2))(x)
x = Conv2D(filters=32, kernel_size=(4, 4), activation='relu', padding='same')(x)
x = MaxPooling2D(pool_size=(2, 2))(x)
x = Flatten()(x)
x = Dense(256, activation='relu')(x)
x = Dense(256, activation='relu')(x)
encoded = Dense(192, activation='linear')(x)

input_enc = Input(shape=(192,))
d = Reshape((8, 8, 3))(input_enc)
d = Conv2DTranspose(filters=32, kernel_size=(4, 4), strides=2, activation='relu', padding='same')(d)
d = Conv2DTranspose(filters=16, kernel_size=(4, 4), strides=2, activation='relu', padding='same')(d)
d = Flatten()(d)
d = Dense(4048, activation='relu')(d)
d = Dense(32 * 32 * 3, activation='sigmoid')(d)
decoded = Reshape((32, 32, 3))(d)

encoder = keras.Model(input_img, encoded, name="encoder")
decoder = keras.Model(input_enc, decoded, name="decoder")
autoencoder = keras.Model(input_img, decoder(encoder(input_img)), name="autoencoder")
autoencoder.compile(optimizer='adam', loss='mean_squared_error', metrics=['accuracy'])

autoencoder.fit(x_train, x_train,
                epochs=10,
                batch_size=64,
                validation_split=0.2,
                shuffle=True)

encoder.save("D:/Pyton/Proriv/NeroClop/model/encoder_v4.h5")
decoder.save("D:/Pyton/Proriv/NeroClop/model/decoder_v4.h5")
autoencoder.save("D:/Pyton/Proriv/NeroClop/model/autoencoder_v4.h5")

img = autoencoder.predict(np.expand_dims(x_train[100], axis=0))

plt.imshow(img.squeeze(), cmap='gray')
plt.show()


