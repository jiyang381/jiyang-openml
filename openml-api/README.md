# Feedzai OpenML API
This module contains the core concepts of the OpenML API.


## Developing

To implement an OpenML provider there are two options:

* Implement the **MachineLearningProvider** interface to load trained ML models to the Feedzai platform. It uses the __MachineLearningModelLoader__ interface to load a model.

* Implement the **Trai