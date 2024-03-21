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

package com.feedzai.openml.util.validate;

import com.feedzai.openml.data.schema.AbstractValueSchema;
import com.feedzai.openml.data.schema.CategoricalValueSchema;
import com.feedzai.openml.data.schema.DatasetSchema;
import com.feedzai.openml.data.schema.FieldSchema;
import com.feedzai.openml.data.schema.NumericValueSchema;
import com.feedzai.openml.provider.descriptor.fieldtype.ParamValidationError;
import com.feedzai.openml.util.data.schema.TestDatasetSchemaBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Performs unit tests on the {@link ValidationUtils#validateCategoricalSchema(DatasetSchema)} method.
 *
 * @author Henrique Costa (henrique.costa@feedzai.com)
 * @since 0.1.0
 */
public class ValidationUtilsValidateCategoricalTest {

    /**
     * Tests that when {@link ValidationUtils#validateCategoricalSchema(DatasetSchema) validating categorical target values} for a schema with no target field,
     * a {@link ParamValidationError} is returned.
     */
    @Test
    public final void testSchemaWithNoTarget() {
        final DatasetSchema schemaWithTarget = TestDatasetSchemaBuilder.builder()
                .withNumericalFields(3)
                .withCategoricalFields(3, ImmutableSet.of("cat1", "cat2"))
                .withCategoricalTarget(true)
                .build();
        final DatasetSchema schema = new DatasetSchema(schemaWithTarget.getFieldSchemas());

        final Optional<ParamValidationError> validationResult = ValidationUtils.validateCategoricalSchema(schema);

        assertThat(validationResult)
                .as("Validating a schema with no target fields fails.")
                .isPresent();
    }

    /**
     * Validates that the utility method accepts a schema with a {@link CategoricalValueSchema} target variable.
     */
    @Test
    public void testValidateCategoricalOK() {
        final Optional<ParamValidationError> validationResult = buildSchemaAndValidateCategorical(
                true,
                ImmutableSet.of("cat1", "cat2")
        );

        assertThat(validationResult)
                .as("Validating a schema with a categorical target variable")
                .isEmpty();
    }

    /**
     * Validates that the utility method rejects a schema with a {@link NumericValueSchema} target variable.
     */
    @Test
    public void testValidateCategoricalNumericalTarget() {
        final Optional<ParamValidationError> valida