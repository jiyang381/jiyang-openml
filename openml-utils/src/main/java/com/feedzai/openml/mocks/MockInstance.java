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

package com.feedzai.openml.mocks;

import com.feedzai.openml.data.Instance;
import com.feedzai.openml.data.schema.AbstractValueSchema;
import com.feedzai.openml.data.schema.DatasetSchema;
import com.feedzai.openml.data.schema.FieldSchema;
import com.feedzai.openml.data.schema.NumericValueSchema;
import com.feedzai.openml.data.schema.StringValueSchema;
import com.feedzai.openml.util.data.ClassificationDatasetSchemaUtil;
import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Objects;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Mock class implementation for an instance.
 * Used for tests and validation of models.
 * <p>
 * By default creates an instance with {@code fieldSize} floating point values ranging [0,10[.
 *
 * @author Luis Reis (luis.reis@feedzai.com)
 * @since 0.1.0
 */
public class MockInstance implements Instance, Serializable {

    /**
     * Range for mock values in numeric fields.
     */
    private static final double
            MAX_NUMERIC_MOCK_VALUE = 10,
            MIN_NUMERIC_MOCK_VALUE = -10;

    /**
     * Number of random bytes to use when generating mock String values.
     */
    private static final int NUM_MOCK_STRING_RANDOM_BYTES = 32;

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = -626