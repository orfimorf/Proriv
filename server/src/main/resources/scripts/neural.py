import keras
import numpy as np
from PIL import Image

img = Image.open(input())

encoder = keras.models.load_model("D:/Pyton/Proriv/NeroClop/model/encoder_v4.h5")

aspect_ratio = img.width / img.height
target_aspect_ratio = 1

if aspect_ratio > target_aspect_ratio:
    new_width = 32
    new_height = round(32 / aspect_ratio)
else:
    new_width = round(32 * aspect_ratio)
    new_height = 32

resized_img = img.resize((new_width, new_height))

# Создаем новое изображение с черным фоном
new_img = Image.new("RGB", [32, 32], "black")
x_offset = (32 - new_width) // 2
y_offset = (32 - new_height) // 2
new_img.paste(resized_img, (x_offset, y_offset))

print(encoder.predict(np.array([new_img])))
