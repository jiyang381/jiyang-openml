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

package com.feedzai.openml.provider;

import com.feedzai.openml.model.MachineLearningModel;
import com.feedzai.openml.provider.descriptor.MLAlgorithmDescriptor;
import com.feedzai.openml.provider.model.MachineLearningModelLoader;

import java.util.Optional;
import java.util.Set;

/**
 * An interface representing a service to provide {@link MachineLearningModel}.
 * This interface is not public because implementations should use one of the sub interfaces available.
 *
 * @param <T> The generic type of {@link MachineLearningModelLoader} this entity is able to retrieve.
 * @author Pedro Rijo (pedro.rijo@feedzai.com)
 * @since 0.1.0
 */
public interface MachineLearningProvider<T extends MachineLe