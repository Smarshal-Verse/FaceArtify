# FaceArtify

Face Shape Detction and Cartoonization App

This app uses a TensorFlow Lite machine learning model trained with CNN and Kaggle datasets to process the given input image and return the face shape. The app also uses a separate ML model to cartoonize the image.

Features

* Identify the face shape of the person in the image.
* Cartoonize the image.
How to Use

* Upload an image of a person's face.
* Click the "Identify Face Shape" button to see the face shape of the person in the image.
* Click the "Cartoonize Image" button to cartoonize the image.


The White-box CartoonGAN Model
![sam1](https://github.com/Smarshal21/FaceArtify/assets/99678760/c5d6620f-4bbb-4303-9ff7-5fa48f253a4d)

* The Cartoonizer app utilizes a Generative Adversarial Network (GAN) model based on the "Learning to Cartoonize Using White-box Cartoon Representations" paper, presented at CVPR 2020. The pretrained weights used in the app are provided by the authors of the paper and can be found in their project GitHub repository.

Face Shape Detection
![sam2](https://github.com/Smarshal21/FaceArtify/assets/99678760/3de80510-b7a3-4f75-9c43-9884c2ee3be5)

* The app employs a TensorFlow Lite machine learning model trained using Convolutional Neural Networks (CNNs) and Kaggle datasets to process input images and return the face shape. The model is capable of identifying various face shapes such as round, square, oval, heart-shaped, and more. By leveraging machine learning techniques, the app accurately recognizes the shape of human faces, enabling users to create cartoon-like representations.

Feel free to contribute, provide feedback, or report any issues you encounter during your use of the Cartoonizer app. Enjoy cartoonizing your images with TensorFlow Lite!
