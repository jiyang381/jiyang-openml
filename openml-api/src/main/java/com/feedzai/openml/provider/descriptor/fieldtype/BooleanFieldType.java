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

package com.feedzai.openml.provider.descriptor.fieldtype;

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Optional;

/**
 * A concrete implementation of a {@link ModelParameterType} for parameters whose values are boolean.
 * Check {@link #validate(String, String)} documentation for valid boolean value representations.
 *
 * @author Pedro Rijo (pedro.rijo@feedzai.com)
 * @since 0.1.0
 */
public class BooleanFieldType implements ModelParameterType {

    /**
     * Defines whether this field defaults to the value true.
     */
    private fi