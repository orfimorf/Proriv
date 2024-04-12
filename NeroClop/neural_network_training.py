import keras
import matplotlib.pyplot as plt  # Импорт библиотеки для визуализации данных

# Импорт необходимых библиотек
import numpy as np
from keras.src.layers import MaxPooling2D, Flatten, Conv2D
from keras.models import Sequential
from keras.layers import Dense


height_img = None
width_img = None

x_train = None
y_train = None

x_test = None
y_test = None

output = None


# Создание модели нейронной сети
model = Sequential()
model.add(Conv2D(16, (8, 8), padding='same', activation='relu', input_shape=(height_img, width_img, 3)))
model.add(MaxPooling2D(4, 4))
model.add(Conv2D(32, (8, 8), padding='same', activation='relu'))
model.add(Conv2D(64, (4, 4), padding='same', activation='relu'))
model.add(Conv2D(128, (2, 2), padding='same', activation='relu'))
model.add(Flatten())
model.add(Dense(128, activation='relu'))
model.add(Dense(output, activation='softmax'))  # Добавление выходного слоя с одним нейроном для регрессии

model.compile(optimizer='adam',
              loss=keras.losses.SparseCategoricalCrossentropy(from_logits=True),
              metrics=['accuracy'])


# Обучение нейронной сети
history = model.fit(x_train, y_train, epochs=8, batch_size=32, validation_split=0.2)  # Обучение модели на данных

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

test_loss, test_acc = model.evaluate(x_test,  y_test, verbose=2)

print('\nTest accuracy:', test_acc)

