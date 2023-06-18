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

package com.feedzai.openml.util.data;

import com.feedzai.openml.data.schema.AbstractValueSchema;
import com.feedzai.openml.data.schema.CategoricalValueSchema;
import com.feedzai.openml.data.schema.DatasetSchema;
import com.feedzai.openml.data.schema.FieldSchema;
import com.feedzai.openml.model.ClassificationMLModel;

import java.util.Optional;
import java.util.function.Function;

/**
 * Utilities for manipulating a {@link DatasetSchema} of a {@link ClassificationMLModel classification ML problem}.
 *
 * @author Nuno Diegues (nuno.diegues@feedzai.com)
 * @since 0.1.0
 */
public final class ClassificationDatasetSchemaUtil {

    /**
     * Private constructor for utility class.
     */
    private ClassificationDatasetSchemaUtil() { }

    /**
     * Gets the number of classes in the given schema's target variable assuming that it is for a classification
     * problem.
     *
     * @param datasetSchema The {@link DatasetSchema}.
     * @return The number of classes on the target variable, if a target variable is defined, or {@link Optional#empty()} otherwise.
     */
    public static Optional<Integer> getNumClassValues(final DatasetSchema datasetSchema) {
        return datasetSchema.getTargetIndex()
                .map(datasetSchema.getFieldSchemas()::get)
                .map(