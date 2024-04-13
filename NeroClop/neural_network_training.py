import os

import keras
import matplotlib.pyplot as plt  # Импорт библиотеки для визуализации данных

# Импорт необходимых библиотек
import numpy as np
from PIL import Image
from keras import Input

from keras.src.layers import MaxPooling2D, Flatten, Conv2D, UpSampling2D, Reshape, Conv2DTranspose
from keras.models import Sequential
from keras.layers import Dense

directory = 'D:/Pyton/Proriv/NeroClop/data_for_incoder_128'


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

    progress_bar(500, i)

    if i >= 500:
        break

# Преобразование списка в массив NumPy
x_train = np.array(x_train)

x_train = x_train / 255

input_img = Input(shape=(128, 128, 3))
x = Conv2D(filters=8, kernel_size=(8, 8), activation='elu', padding='same')(input_img)
x = MaxPooling2D(pool_size=(2, 2))(x)
x = Conv2D(filters=16, kernel_size=(8, 8), activation='elu', padding='same')(x)
# x = MaxPooling2D(pool_size=(2, 2))(x)
# x = Conv2D(filters=32, kernel_size=(8, 8), activation='elu', padding='same')(x)
# x = Conv2D(filters=64, kernel_size=(8, 8), activation='elu', padding='same')(x)
x = MaxPooling2D(pool_size=(2, 2))(x)
x = Flatten()(x)
encoded = Dense(48, activation='linear')(x)

input_enc = Input(shape=(48,))
d = Reshape((4, 4, 3))(input_enc)
d = Conv2DTranspose(filters=16, kernel_size=(12, 12), strides=2, activation='elu', padding='same')(d)
d = Conv2DTranspose(filters=8, kernel_size=(12, 12), strides=2, activation='elu', padding='same')(d)
# d = Conv2DTranspose(filters=16, kernel_size=(16, 16), strides=2, activation='elu', padding='same')(d)
d = Conv2DTranspose(filters=4, kernel_size=(12, 12), strides=2, activation='elu', padding='same')(d)
d = Flatten()(d)
d = Dense(128 * 128 * 3, activation='sigmoid')(d)
decoded = Reshape((128, 128, 3))(d)

encoder = keras.Model(input_img, encoded, name="encoder")
decoder = keras.Model(input_enc, decoded, name="decoder")
autoencoder = keras.Model(input_img, decoder(encoder(input_img)), name="autoencoder")
autoencoder.compile(optimizer='adam', loss='mean_squared_error')

autoencoder.fit(x_train, x_train,
                epochs=5,
                batch_size=32,
                shuffle=True)

encoder.save("D:/Pyton/Proriv/NeroClop/model/encoder_v2")
decoder.save("D:/Pyton/Proriv/NeroClop/model/decoder_v2")
autoencoder.save("D:/Pyton/Proriv/NeroClop/model/autoencoder_v2")



