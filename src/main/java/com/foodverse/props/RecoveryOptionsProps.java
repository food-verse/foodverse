package com.foodverse.props;

import com.foodverse.models.Address;
import com.foodverse.utility.core.Props;

public record RecoveryOptionsProps(
    String name,
    Address address,
    String phone,
    String email,
    String password
) implements Props {}
