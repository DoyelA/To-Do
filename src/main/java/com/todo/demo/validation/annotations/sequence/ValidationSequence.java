package com.todo.demo.validation.annotations.sequence;

import com.todo.demo.validation.annotations.groups.*;
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
