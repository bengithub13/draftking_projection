package com.draftking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
/* @author : Ben
 * @since :11/22/2015
 * 
 * THis abstract entity does not abstract ID column.  Subclasses have to define their own ID>
 * IN JPA unlike Hibernate can n not override the sequence generator annotation.  We can not pick up  subclasses defined sequence generate annotation 
 * in hibernate we could define the sequence generator with @SequencerGenerator annotation and have the superclass pick it up in the @GeneratedValue annotation
 * In JPA the sequence is global to the entire persistent context so we have to either use the same Sequence genrator or define the ID in each domain.
 * I have decided to use a master_dratking_seq for all entities because I wanted to abstract away the ID column and all the equal method boilerplate code.
 */
@MappedSuperclass
public abstract class AbstractDomain implements Serializable {
	public static final String GENERATED_VALUE="SEQ";
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = GENERATED_VALUE )   // this is defined in subclass.
	@SequenceGenerator(name = AbstractDomain.GENERATED_VALUE, sequenceName = "master_draftking_seq")
	@Column(name = "ID")
	private Long id;
	private static final long serialVersionUID=2L;
	protected AbstractDomain() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null) {
			if (this == o) {
				return true;
			}
			if (this.getClass() == o.getClass()) {
				if (this.getId() == ((AbstractDomain) o).getId()) {
					return true;
				}
			}
		}
		return false;
	}
}