/*
 * Bird-Registry
 * Bird registry
 *
 * OpenAPI spec version: 
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.saltside.bird.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Bird properties
 */
@ApiModel(description = "Bird properties")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-08-19T05:47:47.983Z")
public class Bird {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("family")
	private String family = null;

	@JsonProperty("continents")
	private List<String> continents = new ArrayList<String>();

	@JsonProperty("added")
	private String added = null;

	@JsonProperty("visible")
	private Boolean visible = false;

	public Bird id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Object id from the database
	 * 
	 * @return id
	 **/
	@JsonProperty("id")
	@ApiModelProperty(value = "Object id from the database")
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Bird name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * English bird name
	 * 
	 * @return name
	 **/
	@JsonProperty("name")
	@ApiModelProperty(value = "English bird name")
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bird family(String family) {
		this.family = family;
		return this;
	}

	/**
	 * Latin bird family name
	 * 
	 * @return family
	 **/
	@JsonProperty("family")
	@ApiModelProperty(value = "Latin bird family name")
	@NotNull
	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public Bird continents(List<String> continents) {
		this.continents = continents;
		return this;
	}

	public Bird addContinentsItem(String continentsItem) {
		this.continents.add(continentsItem);
		return this;
	}

	/**
	 * Continents the bird exist on
	 * 
	 * @return continents
	 **/
	@JsonProperty("continents")
	@ApiModelProperty(value = "Continents the bird exist on")
	@NotNull
	@Size(min = 1)
	public List<String> getContinents() {
		return continents;
	}

	public void setContinents(List<String> continents) {
		this.continents = continents;
	}

	public Bird added(String added) {
		this.added = added;
		return this;
	}

	/**
	 * Date the bird was added to the registry. Format YYYY-MM-DD
	 * 
	 * @return added
	 **/
	@JsonProperty("added")
	@ApiModelProperty(value = "Date the bird was added to the registry. Format YYYY-MM-DD")
	@NotNull
	public String getAdded() {
		return added;
	}

	public void setAdded(String added) {
		this.added = added;
	}

	public Bird visible(Boolean visible) {
		this.visible = visible;
		return this;
	}

	/**
	 * Determines if the bird should be visible in lists
	 * 
	 * @return visible
	 **/
	@JsonProperty("visible")
	@ApiModelProperty(value = "Determines if the bird should be visible in lists")
	@NotNull
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Bird birdResponse = (Bird) o;
		return Objects.equals(this.id, birdResponse.id) && Objects.equals(this.name, birdResponse.name)
				&& Objects.equals(this.family, birdResponse.family)
				&& Objects.equals(this.continents, birdResponse.continents)
				&& Objects.equals(this.added, birdResponse.added) && Objects.equals(this.visible, birdResponse.visible);
	}

	@JsonIgnore
	public boolean isValid() {
		if (isEmpty(name) || isEmpty(family) || isEmpty(continents)) {
			return false;
		}
		return true;
	}

	/**
	 * @param input
	 * @return
	 */
	private boolean isEmpty(List<String> input) {
		return input == null || input.size() == 0;
	}

	/**
	 * @param input
	 * @return
	 */
	private boolean isEmpty(String input) {
		return input == null || input.trim().isEmpty();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, family, continents, added, visible);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BirdResponse {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    family: ").append(toIndentedString(family)).append("\n");
		sb.append("    continents: ").append(toIndentedString(continents)).append("\n");
		sb.append("    added: ").append(toIndentedString(added)).append("\n");
		sb.append("    visible: ").append(toIndentedString(visible)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}