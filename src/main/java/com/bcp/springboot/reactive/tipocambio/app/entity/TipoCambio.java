package com.bcp.springboot.reactive.tipocambio.app.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tipocambio")
public class TipoCambio implements Persistable<Integer> {

	@Id
	@Column("id")
	private Integer id;
	@Column("montoOrigen")
	private Double montoOrigen;
	@Column("monedaOrigen")
	private String monedaOrigen;
	@Column("montoDestino")
	private Double montoDestino;
	@Column("monedaDestino")
	private String monedaDestino;

	@Column("usuarioCreacion")
	private String usuarioCreacion;

	@Column("usuarioModificacion")
	private String usuarioModificacion;

	public Double getMontoOrigen() {
		return montoOrigen;
	}

	public void setMontoOrigen(Double montoOrigen) {
		this.montoOrigen = montoOrigen;
	}

	public String getMonedaOrigen() {
		return monedaOrigen;
	}

	public void setMonedaOrigen(String monedaOrigen) {
		this.monedaOrigen = monedaOrigen;
	}

	public Double getMontoDestino() {
		return montoDestino;
	}

	public void setMontoDestino(Double montoDestino) {
		this.montoDestino = montoDestino;
	}

	public String getMonedaDestino() {
		return monedaDestino;
	}

	public void setMonedaDestino(String monedaDestino) {
		this.monedaDestino = monedaDestino;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	@Transient
	private boolean isNew;

	@Override
	@Transient
	public boolean isNew() {
		return this.isNew || id == null;
	}

	public TipoCambio setAsNew() {
		this.isNew = true;
		return this;
	}

	@Override
	public Integer getId() {
		return id;
	}

}
