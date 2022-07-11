package validation.annotations.sequence;

import validation.annotations.groups.*;

import javax.validation.GroupSequence;

@GroupSequence(value={
        NotNullGroup.class,
        NotEmptyGroup.class,
        NotBlankGroup.class,
        LengthGroup.class,
        DBConstraints.class
})
public interface ValidationSequence {
}
