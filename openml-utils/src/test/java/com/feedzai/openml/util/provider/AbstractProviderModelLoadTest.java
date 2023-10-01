/*
 * Copyright 2018 Feedzai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.feedzai.openml.util.provider;

import com.feedzai.openml.data.schema.DatasetSchema;
import com.feedzai.openml.model.ClassificationMLModel;
import com.feedzai.openml.provider.MachineLearningProvider;
import com.feedzai.openml.provider.descriptor.fieldtype.ParamValidationError;
import com.feedzai.openml.provider.exception.ModelLoadingException;
import com.feedzai.openml.provider.model.MachineLearningModelLoader;
import com.feedzai.openml.util.algorithm.MLAlgorithmEnum;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Contains tests for model loading with a Provider.
 *
 * @param <M> The type of a class that extends {@link ClassificationMLModel}.
 * @param <L> The type of a class that extends {@link MachineLearningModelLoader}.
 * @param <P> The type of a class that extends {@link MachineLearningProvider}.
 *
 * @author Luis Reis (luis.reis@feedzai.com)
 * @author Paulo Pereira (paulo.pereira@feedzai.com)
 * @since 0.1.0
 */
public abstract class AbstractProviderModelLoadTest<M extends ClassificationMLModel,
                                                    L extends MachineLearningModelLoader<M>,
                                                    P extends MachineLearningProvider<L>>
        extends Abstrac