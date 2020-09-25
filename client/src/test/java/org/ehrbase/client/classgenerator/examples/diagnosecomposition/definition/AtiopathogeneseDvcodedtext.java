package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class AtiopathogeneseDvcodedtext implements AtiopathogeneseChoice {
    @Path("|defining_code")
    private Definingcode definingcode;

    public void setDefiningcode(Definingcode definingcode) {
        this.definingcode = definingcode;
    }

    public Definingcode getDefiningcode() {
        return this.definingcode;
    }
}
