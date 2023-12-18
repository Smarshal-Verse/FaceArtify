# FaceArtify: Face Shape Detection and Cartoonization App

FaceArtify is an innovative Android application that combines advanced machine learning with creative features. The app utilizes a TensorFlow Lite machine learning model trained with Convolutional Neural Networks (CNN) and Kaggle datasets to identify the face shape in a given image. Additionally, it employs a separate machine learning model based on the "Learning to Cartoonize Using White-box Cartoon Representations" paper, presented at CVPR 2020, to cartoonize the image.

## Features

- **Face Shape Identification:**
  Upload an image of a person's face and click the "Identify Face Shape" button to reveal the specific face shape of the individual.

- **Cartoonization:**
  Click the "Cartoonize Image" button to apply a cartoon-like transformation to the uploaded image using the White-box CartoonGAN model.

## How to Use

1. Upload an image of a person's face.
2. Click the "Identify Face Shape" button to determine the face shape.
3. Click the "Cartoonize Image" button to apply the cartoonization effect.

### The White-box CartoonGAN Model
![White-box CartoonGAN Model](https://github.com/Smarshal21/FaceArtify/assets/99678760/c5d6620f-4bbb-4303-9ff7-5fa48f253a4d)

The Cartoonizer app utilizes a Generative Adversarial Network (GAN) model based on the "Learning to Cartoonize Using White-box Cartoon Representations" paper, presented at CVPR 2020. The pretrained weights used in the app are provided by the authors of the paper and can be found in their project GitHub repository.

### Face Shape Detection
![Face Shape Detection](https://github.com/Smarshal21/FaceArtify/assets/99678760/3de80510-b7a3-4f75-9c43-9884c2ee3be5)

The app employs a TensorFlow Lite machine learning model trained using Convolutional Neural Networks (CNNs) and Kaggle datasets to process input images and return the face shape. The model accurately identifies various face shapes such as round, square, oval, heart-shaped, and more.

Feel free to contribute, provide feedback, or report any issues you encounter during your use of the FaceArtify app. Enjoy the creative process of cartoonizing your images with TensorFlow Lite!

## Contributing

Contributions are welcome! Feel free to fork the repository, make improvements, and submit pull requests. If you encounter any issues, please open an issue to discuss and resolve them.

