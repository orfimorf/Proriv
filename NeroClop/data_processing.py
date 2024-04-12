import json

from PIL import Image
import os
import numpy as np


def convert_images(input_directory, output_directory, output_format, output_size):
    # data = []
    for root, _, files in os.walk(input_directory):
        for file in files:
            full_path = os.path.join(root, file)
            # Проверяем, что файл является изображением
            if file.lower().endswith(('.png', '.jpg', '.jpeg', '.bmp')):
                # Открываем изображение
                img = Image.open(full_path)

                # Масштабируем изображение подгоняя по высоте и ширине
                aspect_ratio = img.width / img.height
                target_aspect_ratio = output_size[0] / output_size[1]
                if aspect_ratio > target_aspect_ratio:
                    new_width = output_size[0]
                    new_height = round(output_size[0] / aspect_ratio)
                else:
                    new_width = round(output_size[1] * aspect_ratio)
                    new_height = output_size[1]

                resized_img = img.resize((new_width, new_height))

                # Создаем новое изображение с черным фоном
                new_img = Image.new("RGB", output_size, "black")
                x_offset = (output_size[0] - new_width) // 2
                y_offset = (output_size[1] - new_height) // 2
                new_img.paste(resized_img, (x_offset, y_offset))

                # Создаем путь для сохранения нового изображения
                if not os.path.exists(output_directory):
                    os.makedirs(output_directory)

                new_file_path = os.path.join(output_directory, os.path.splitext(file)[0] + '.' + output_format.lower())

                # Сохраняем новое изображение
                new_img.save(new_file_path, format=output_format)

    #             data.append(np.array(new_img))
    #
    # with open("D:/Pyton/Proriv/NeroClop/data.json", "w") as file:
    #     json.dump(data, file)



# Замените 'путь_к_исходной_папке' и 'путь_к_целевой_папке' на соответствующие пути
convert_images('D:/Робочий стол/train', 'D:/Pyton/Proriv/NeroClop/data_for_incoder', 'JPEG', (1024, 1024))
