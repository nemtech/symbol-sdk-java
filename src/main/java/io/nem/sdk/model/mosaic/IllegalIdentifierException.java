/*
 * Copyright 2018 NEM
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
 */

package io.nem.sdk.model.mosaic;

/**
 * IllegalIdentifierException is thrown when a Namespace Name or Mosaic Name is not valid.
 *
 * @since 1.0
 */
public class IllegalIdentifierException extends RuntimeException {

    public IllegalIdentifierException() {
    }

    public IllegalIdentifierException(String s) {
        super(s);
    }

    public IllegalIdentifierException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public IllegalIdentifierException(Throwable throwable) {
        super(throwable);
    }

    public IllegalIdentifierException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
