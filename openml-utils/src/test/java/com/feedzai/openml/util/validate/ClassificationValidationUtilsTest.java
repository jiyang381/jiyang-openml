
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

import com.feedzai.openml.data.Instance;
import com.feedzai.openml.data.schema.DatasetSchema;
import com.feedzai.openml.model.ClassificationMLModel;
import com.feedzai.openml.model.MachineLearningModel;
import com.feedzai.openml.provider.descriptor.fieldtype.ParamValidationError;
import com.feedzai.openml.provider.exception.ModelLoadingException;
import com.feedzai.openml.provider.model.MachineLearningModelLoader;
import com.feedzai.openml.util.data.schema.TestDatasetSchemaBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for the {@link ClassificationValidationUtils}.
 *
 * @author Nuno Diegues (nuno.diegues@feedzai.com)
 * @since 0.1.0
 */
public class ClassificationValidationUtilsTest {

    /**
     * The schema for the data to classify.
     */
    private static final DatasetSchema SCHEMA = TestDatasetSchemaBuilder.builder()
            .withCategoricalFields(2)
            .withNumericalFields(3)
            .withCategoricalTarget()
            .build();

    /**
     * A valid path of a file.
     *
     * @since 0.3.0
     */
    private static Path VALID_PATH;

    /**
     * A valid {@link Map} of parameters to valid that a model can be loaded.
     *
     * @since 0.3.0
     */
    private static Map<String, String> VALID_PARAM_MAP = ImmutableMap.of("k1", "v2");

    /**
     * A invalid {@link Map} of parameters to valid that a model cannot be loaded.
     *
     * @since 0.3.0
     */
    private static Map<String, String> INVALID_PARAM_MAP = ImmutableMap.of();

    /**
     * Message of the error that will be thrown during the validation of a model to load.
     *
     * @since 0.3.0
     */
    private static final String INVALID_MODEL_TO_LOAD_MSG = "map is empty";

    /**
     * Part of the error message when a validation method is called with a null parameter.
     *
     * @since 0.3.0
     */
    private static final String NULL_ERROR_MSG = "cannot be null";

    @BeforeClass
    public static void beforeClass() throws IOException {
        VALID_PATH = Files.createTempFile("temp", ".out");
        VALID_PATH.toFile().deleteOnExit();
    }

    /**
     * Tests that a valid classification model is correctly assessed as such.
     */
    @Test
    public void testValidModel() {
         assertThatCode(() -> ClassificationValidationUtils.validateClassificationModel(
                SCHEMA,
                getClassificationModelFor(
                        () -> new double[] { 0.25, 0.75 },
                        () -> 1
                )
        )).doesNotThrowAnyException();
    }

    /**
     * Tests that a classification model that cannot yield a distribution is correctly detected.
     */
    @Test
    public void testModelInvalidDistribution() {
        assertThatThrownBy(() -> ClassificationValidationUtils.validateClassificationModel(
                SCHEMA,
                getClassificationModelFor(
                        () -> {
                            throw new RuntimeException("Failure to calculate distribution");
                        },
                        () -> 1
                )
        )).isInstanceOf(ModelLoadingException.class);
    }

    /**
     * Tests that a classification model that cannot classify is correctly detected.
     */
    @Test
    public void testModelInvalidClassification() {
        assertThatThrownBy(() -> ClassificationValidationUtils.validateClassificationModel(
                SCHEMA,
                getClassificationModelFor(
                        () -> new double[] { 0.25, 0.75 },
                        () -> {
                            throw new RuntimeException("Failure to calculate distribution");
                        }
                )
        )).isInstanceOf(ModelLoadingException.class);
    }

    /**
     * Tests that a model to load is successfully validated when the parameters are valid.