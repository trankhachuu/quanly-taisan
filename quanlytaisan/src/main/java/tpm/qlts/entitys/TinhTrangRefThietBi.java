package tpm.qlts.entitys;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the TT_TB database table.
 * 
 */
@Entity
@Table(name = "TT_TB")
@NamedQuery(name = "TinhTrangRefThietBi.findAll", query = "SELECT t FROM TinhTrangRefThietBi t")
public class TinhTrangRefThietBi implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TinhTrangRefThietBiPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DenNgay")
	private Date denNgay;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TuNgay")
	private Date tuNgay;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Timestame", insertable = false, updatable = false)
	private Date timestame;

	// bi-directional many-to-one association to ThietBi
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaThietBi", updatable = false, insertable = false)
	private ThietBi thietBi;

	// bi-directional many-to-one association to TinhTrang
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MaTinhTrang", updatable = false, insertable = false)
	private TinhTrang tinhTrang;

	public TinhTrangRefThietBi() {
	}

	public Date getTimestame() {
		return timestame;
	}

	public void setTimestame(Date timestame) {
		this.timestame = timestame;
	}

	public TinhTrangRefThietBiPK getId() {
		return this.id;
	}

	public void setId(TinhTrangRefThietBiPK id) {
		this.id = id;
	}

	public Date getDenNgay() {
		return this.denNgay;
	}

	public void setDenNgay(Date denNgay) {
		this.denNgay = denNgay;
	}

	public Date getTuNgay() {
		return this.tuNgay;
	}

	public void setTuNgay(Date tuNgay) {
		this.tuNgay = tuNgay;
	}

	public ThietBi getThietBi() {
		return this.thietBi;
	}

	public void setThietBi(ThietBi thietBi) {
		this.thietBi = thietBi;
	}

	public TinhTrang getTinhTrang() {
		return this.tinhTrang;
	}

	public void setTinhTrang(TinhTrang tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

}