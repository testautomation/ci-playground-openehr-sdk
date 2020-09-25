/*
 * Copyright (c) 2019 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project EHRbase
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

package org.ehrbase.validation.constraints;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.validation.constraints.wrappers.CArchetypeConstraint;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class DvCodedTextTest extends ConstraintTestBase {

    @Before
    public void setUp(){
        try {
            setUpContext("./src/test/resources/constraints/dvcodedtext.xml");
        } catch (Exception e){
            fail();
        }
    }

    @Test
    public void testConstraintValidation() {
        CodePhrase codePhrase = new CodePhrase(new TerminologyId("local"), "at0008");
        DvCodedText dvCodedText = new DvCodedText("1234", codePhrase);

        try {
            new CArchetypeConstraint(null).validate("test", dvCodedText, archetypeconstraint);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testConstraintValidationInvalidCodeString() {
        CodePhrase codePhrase = new CodePhrase(new TerminologyId("local"), "at0022");
        DvCodedText dvCodedText = new DvCodedText("1234", codePhrase);

        try {
            new CArchetypeConstraint(null).validate("test", dvCodedText, archetypeconstraint);
            fail("Undetected wrong code string");
        }
        catch (Exception e){

        }
    }

    @Test
    public void testConstraintValidationBadOpenEHRTerminology() {
        CodePhrase codePhrase = new CodePhrase(new TerminologyId("openehr"), "xvy1");
        DvCodedText dvCodedText = new DvCodedText("1234", codePhrase);

        try {
            new CArchetypeConstraint(null).validate("test", dvCodedText, archetypeconstraint);
            fail("undetected wrong terminology id");
        }
        catch (Exception e){

        }
    }
}
