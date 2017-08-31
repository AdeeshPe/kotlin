/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.backend.js.intrinsics

import org.jetbrains.kotlin.backend.js.context.FunctionIntrinsic
import org.jetbrains.kotlin.backend.js.context.IrTranslationContext
import org.jetbrains.kotlin.backend.js.util.buildJs
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.js.backend.ast.JsExpression
import org.jetbrains.kotlin.js.backend.ast.JsNullLiteral
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe

object ArrayOfNullsIntrinsic : FunctionIntrinsic {
    private val matchFqName = FqName("kotlin.arrayOfNulls")

    override fun isApplicable(descriptor: FunctionDescriptor): Boolean = descriptor.fqNameSafe == matchFqName

    override fun apply(
            context: IrTranslationContext, call: IrCall,
            dispatchReceiver: JsExpression?, extensionReceiver: JsExpression?, arguments: List<JsExpression>
    ): JsExpression? =
            buildJs { "Kotlin".dotPure("newArray").invoke(arguments[0], JsNullLiteral()) }
}