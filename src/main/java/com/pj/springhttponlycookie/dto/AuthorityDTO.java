package com.pj.springhttponlycookie.dto;

import java.io.Serial;
import java.io.Serializable;

public record AuthorityDTO(String name) implements Serializable
{
	@Serial
	private static final long serialVersionUID = 4415682316116160764L;
}
