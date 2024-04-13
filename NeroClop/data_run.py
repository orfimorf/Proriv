import keras
import numpy as np
import pandas as pd
from PIL import Image

data = pd.read_csv("train.csv", sep=";")

encoder = keras.models.load_model("D:/Pyton/Proriv/NeroClop/model/encoder_v4.h5")

key_vec = []
data = np.array(data)

f = open("D:/Pyton/Proriv/NeroClop/vectors.txt", "a+")

for i in data:
    img = Image.open(f"D:/Робочий стол/train/{i[0]}/{i[4]}")

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

    vec = encoder.predict(np.array([new_img]))
    t = f"{i[4]}; "

    for i in vec[0]:
        t += f"{i} "

    t += "\n"
    f.write(t)





