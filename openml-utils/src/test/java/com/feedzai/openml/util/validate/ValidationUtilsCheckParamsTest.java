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

import com.feedzai.openml.provider.descriptor.MLAlgorithmDescriptor;
import com.feedzai.openml.provider.descriptor.MachineLearningAlgorithmType;
import com.feedzai.openml.provider.descriptor.ModelParameter;
import com.feedzai.openml.provider.descriptor.fieldtype.ModelParameterType;
import com.feedzai.openml.provider.descriptor.fieldtype.ParamValidationError;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link ValidationUtils} methods that validate model parameters.
 *
 * @author Henrique Costa (henrique.costa@feedzai.com)
 * @since 0.1.0
 */
public class ValidationUtilsCheckParamsTest {

    /**
     * {@link ModelParameterType} implementation that always considers the values valid.
     */
    private static final ModelParameterType ALWAYS_VALID_PARAMETER_TYPE = (name, value) -> Optional.empty();

    /**
     * Ensures that {@link ValidationUtils#checkParams(MLAlgorithmDescriptor, Map)} returns an error when a mandatory
     * parameter is missing.
     */
    @Test
    public void testCheckParamsWithMissingMandatory() {
        final MLAlgorithmDescriptor descriptor = getMlAlgorithmDescriptor(2, 1, ALWAYS_VALID_PARAMETER_TYPE);
        final Map<Boolean, List<ModelParameter>> paramDescriptors = parametersByMandatory(descriptor);
        final ModelParameter optionalParam = paramDescriptors.get(false).get(0);
        final ModelParameter mandatoryParam = paramDescriptors.get(true).get(0);

        validateSingleErrorWithDescription(
                descriptor,
                ImmutableMap.of(optionalParam.getName(), "somevalue"),
                mandatoryParam.getName()
        );
    }

    /**
     * Ensures that {@link ValidationUtils#checkParams(MLAlgorithmDescriptor, Map)} propagates the error returned by a
     * failing parameter validation.
     */
    @Test
    public void testCheckParamsFailingValidations() {
        final String ERROR_MESSAGE = "ERROR";
        final MLAlgorithmDescriptor descriptor = getMlAlgorithmDescriptor(
                1,
                1,
                (name, value) -> Optional.of(new ParamValidationError(ERROR_MESSAGE))
        );

        final Map<Boolean, List<ModelParameter>> paramDescriptors = parametersByMandatory(descriptor);
        final ModelParameter param = paramDescriptors.get(true).get(0);

        validateSingleErrorWithDescription(descriptor, ImmutableMap.of(param.getName(), "somevalue"), ERROR_MESSAGE);
    }

    /**
     * Ensures that {@link ValidationUtils#checkParams(MLAlgorithmDescriptor, Map)} returns OK when all para