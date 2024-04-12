import keras
import matplotlib.pyplot as plt  # Импорт библиотеки для визуализации данных

# Импорт необходимых библиотек
import numpy as np
from keras.src.layers import MaxPooling2D, Flatten, Conv2D, UpSampling2D
from keras.models import Sequential
from keras.layers import Dense


height_img = None
width_img = None

fashion_mnist = keras.datasets.fashion_mnist
(x_train, y_train), (x_test, y_test) = fashion_mnist.load_data()


output = 30

x_train = x_train / 255
x_test = x_test / 255

# Создание модели нейронной сети
input_img = keras.Input(shape=(28, 28, 1))

x = Conv2D(16, (3, 3), activation='relu', padding='same')(input_img)
x = MaxPooling2D((2, 2), padding='same')(x)
x = Conv2D(8, (3, 3), activation='relu', padding='same')(x)
x = MaxPooling2D((2, 2), padding='same')(x)
x = Conv2D(8, (3, 3), activation='relu', padding='same')(x)
encoded = MaxPooling2D((2, 2), padding='same')(x)

# at this point the representation is (4, 4, 8) i.e. 128-dimensional

x = Conv2D(8, (3, 3), activation='relu', padding='same')(encoded)
x = UpSampling2D((2, 2))(x)
x = Conv2D(8, (3, 3), activation='relu', padding='same')(x)
x = UpSampling2D((2, 2))(x)
x = Conv2D(16, (3, 3), activation='relu')(x)
x = UpSampling2D((2, 2))(x)
decoded = Conv2D(1, (3, 3), activation='sigmoid', padding='same')(x)

model = keras.Model(input_img, decoded)
model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])

# Обучение нейронной сети
history = model.fit(x_train, x_train, epochs=8, batch_size=32, validation_split=0.2)  # Обучение модели на данных

# Построение графиков точности и потерь
plt.figure(figsize=(12, 6))  # Создание фигуры для графиков


# График точности
plt.subplot(1, 2, 1)  # Создание первого подграфика
plt.plot(history.history['accuracy'], label='accuracy')  # Построение графика точности
plt.plot(history.history['val_accuracy'], label='Validation Accuracy')  # Построение графика валидационной точности
plt.title('График точности обучения')  # Заголовок графика
plt.xlabel('Эпохи')  # Метка оси X
plt.ylabel('Accuracy')  # Метка оси Y
plt.legend()  # Добавление легенды

# График потерь
plt.subplot(1, 2, 2)  # Создание второго подграфика
plt.plot(history.history['loss'], label='Loss')  # Построение графика потерь
plt.plot(history.history['val_loss'], label='Validation Loss')  # Построение графика валидационной потери
plt.title('График потерь обучения')  # Заголовок графика
plt.xlabel('Эпохи')  # Метка оси X
plt.ylabel('Loss')  # Метка оси Y
plt.legend()  # Добавление легенды

plt.show()  # Отображение графиков

predict = model.predict(x_test)

print(np.argmax(predict[0]))
print(y_test[0])

plt.figure()
plt.imshow(predict[0])
plt.colorbar()
plt.grid(False)
plt.show()

plt.figure()
plt.imshow(x_test[0])
plt.colorbar()
plt.grid(False)
plt.show()


test_loss, test_acc = model.evaluate(x_test,  y_test, verbose=2)

print('\nTest accuracy:', test_acc)

