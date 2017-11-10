/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadepro.developer.controller;

import javax.annotation.Resource;


import javax.sql.DataSource;

 





import org.springframework.http.ResponseEntity;
//import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
 
import org.springframework.security.access.annotation.Secured;



import com.hadepro.developer.model.Developer;
import com.hadepro.developer.model.DummyMaster;
import com.hadepro.developer.model.Kategori;
import com.hadepro.developer.model.Konsumen;
import com.hadepro.developer.model.ModelBayar;
import com.hadepro.developer.model.Pembayaran;
import com.hadepro.developer.model.Perumahan;
import com.hadepro.developer.model.Produk;
import com.hadepro.developer.model.Proyek;
import com.hadepro.developer.model.Rekap_piutang;
import com.hadepro.developer.model.Role;
import com.hadepro.developer.model.Roles;
import com.hadepro.developer.model.Tenor;
import com.hadepro.developer.model.Tipe;
import com.hadepro.developer.model.Transaksi;
import com.hadepro.developer.model.User;
import com.hadepro.developer.vo.master.DeveloperListVO;
import com.hadepro.developer.vo.master.KonsumenListVO;
import com.hadepro.developer.vo.master.PerumahanListVO;
import com.hadepro.developer.vo.master.ProdukListVO;
import com.hadepro.developer.vo.master.ProyekListVO;
import com.hadepro.developer.vo.master.UserListVO;
import com.hadepro.developer.vo.proses.PembayaranListVO;
import com.hadepro.developer.vo.proses.RekapPiutangListVO;
import com.hadepro.developer.vo.proses.TransaksiListVO;

import static java.lang.Math.ceil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class LocalDB {

	// @Autowired
	// private ContactRepository contactRepository;

	private JdbcTemplate jdbcTemplate;
	//static final Logger debugLog = Logger.getLogger("debugLogger");

	 @Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	 
	/* JdbcTemplate setter
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	    {
	        this.jdbcTemplate = jdbcTemplate;
	    }*/
	
	public LocalDB() {
	}

	public int ContactCount() {
		Integer r = this.jdbcTemplate.queryForObject(
				"select count(*) from contact", new Object[] {}, Integer.class);
		return r;
	}

	public ProyekListVO getListAllProyek(int page, int maxResults) {
		String sql = "select a.*, b.nama as nama_developer from proyek a, developer b where a.id_developer=b.id limit " + maxResults + " offset "
				+ (page * maxResults);
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		List<Proyek> rl = new ArrayList<Proyek>();
		for (Map row : rows) {
			Proyek r = new Proyek();
			Developer developer = new Developer();
			developer.setNama((String) row.get("nama_developer"));;
			r.setDeveloper(developer);
			r.setId_proyek((int) row.get("id_proyek"));
			r.setNama_proyek((String) row.get("nama_proyek"));
			r.setLokasi_proyek((String) row.get("lokasi_proyek"));
			r.setLuas((String) row.get("luas"));
			rl.add(r);
		}

		ProyekListVO lstProyek = new ProyekListVO();
		lstProyek.setProyeks(rl);
		long totproyek = countMasterProyek();
		if (totproyek > 0) {
			lstProyek
					.setPagesCount((int) ceil((double) totproyek / maxResults));
		}
		lstProyek.setTotalProyeks(totproyek);
		return lstProyek;
	}

	private long countMasterProyek() {
		Integer r = this.jdbcTemplate.queryForObject("select count(*) from proyek", Integer.class);
		return r;
	}

	public ProyekListVO findProyekByNameLike(int page, int maxResults,
			String name) {
		long count = CountMasterProyekByName(name);
		String qu = "select a.*,b.nama as nama_developer from proyek a, developer b where upper(nama_proyek) like upper(?) and b.id=a.id_developer limit "
				+ maxResults + " offset " + (page * maxResults);
		List<Proyek> listContact = jdbcTemplate.query(qu,new Object[]{name+"%"},
				new RowMapper<Proyek>() {
					public Proyek mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Developer developer = new Developer();
						developer.setNama(rs.getString("nama_developer"));;
						
						Proyek room = new Proyek();
						room.setId_developer(rs.getInt("id_developer"));
						room.setDeveloper(developer);
						room.setId_proyek(rs.getInt("id_proyek"));
						room.setNama_proyek(rs.getString("nama_proyek"));
						room.setLokasi_proyek(rs.getString("lokasi_proyek"));
						room.setLuas(rs.getString("luas"));
						return room;
					}
				});

		ProyekListVO cl = new ProyekListVO();
		if (count > 0) {
			cl.setPagesCount((int) ceil((double) count / maxResults));
		}

		cl.setTotalProyeks(count);
		cl.setProyeks(listContact);
		return cl;
	}

	public long CountMasterProyekByName(String name) {
		String sql = "select count(*) from proyek where upper(nama_proyek) like upper(?)  ";
		Integer r = this.jdbcTemplate.queryForObject(sql , new Object[]{name+"%"},Integer.class);
		return r;
	}

	public void insertProyek(Proyek contact) {
		String sql = "INSERT INTO proyek "
				+ "(id_developer,nama_proyek, lokasi_proyek, luas) VALUES (?, ?, ?, ?)";
		Connection conn = null;

		try {
			conn = this.jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, contact.getId_developer());
			ps.setString(2, contact.getNama_proyek());
			ps.setString(3, contact.getLokasi_proyek());
			ps.setString(4, contact.getLuas());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void updateProyek(Proyek contact) {
		String sql = "update proyek set nama_proyek = ?, lokasi_proyek = ? , luas= ?  where  id_proyek = ?";
		jdbcTemplate.update(sql, new Object[] { contact.getNama_proyek(),contact.getLokasi_proyek(),contact.getLuas(),contact.getId_proyek() });		
		
	}
	@Secured("ROLE_ADMIN")
	public void deleteProyek(String IdProyek) {		
		  String sql = "delete from proyek where  id_proyek = ?";
		  jdbcTemplate.update(sql, new Object[] { IdProyek});
	}

	public  int getMaxResultPage(String s) {
		String sql = "select var_value from varconfig where var_name = ?  ";
		Integer r = Integer.parseInt(this.jdbcTemplate.queryForObject(sql , new Object[]{s},String.class));
		return r;
	}

	public PerumahanListVO getListAllPerumahan(int page, int maxResults) {
		String sql = "select * from perumahan  limit " + maxResults + " offset "
				+ (page * maxResults);
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		List<Perumahan> rl = new ArrayList<Perumahan>();
		for (Map row : rows) {
			Perumahan r = new Perumahan();
			r.setId_proyek((String) row.get("id_proyek"));
			r.setId_perumahan((String) row.get("id_perumahan"));
			r.setNama_perumahan((String) row.get("nama_perumahan"));
			r.setTipe_perumahan((String) row.get("tipe_perumahan"));
			r.setJumlah_unit((int) row.get("jumlah_unit"));
			rl.add(r);
		}

		PerumahanListVO lstPerumahan = new PerumahanListVO();
		lstPerumahan.setPerumahans(rl);
		long totperumahan = countMasterPerumahan();
		if (totperumahan > 0) {
			lstPerumahan.setPagesCount((int) ceil((double) totperumahan / maxResults));
		}
		lstPerumahan.setTotalPerumahans(totperumahan);
		return lstPerumahan;
	}

	private long countMasterPerumahan() {
		Integer r = this.jdbcTemplate.queryForObject("select count(*) from perumahan", Integer.class);
		return r;
	}

	public void insertPerumahan(Perumahan contact) {
		String sql = "INSERT INTO perumahan "
				+ "(id_proyek, id_perumahan, nama_perumahan, tipe_perumahan, jumlah_unit) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;

		try {
			conn = this.jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, contact.getId_proyek());
			ps.setString(2, contact.getId_perumahan());
			ps.setString(3, contact.getNama_perumahan());
			ps.setString(4, contact.getTipe_perumahan());
			ps.setInt(5, contact.getJumlah_unit());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public PerumahanListVO findPerumahanByNameLike(int page, int maxResults,
			String name) {
		long count = CountMasterPerumahanByName(name);
		String qu = "select * from perumahan where upper(nama_perumahan) like upper(?) limit "
				+ maxResults + " offset " + (page * maxResults);
		List<Perumahan> listContact = jdbcTemplate.query(qu,new Object[]{name+"%"},
				new RowMapper<Perumahan>() {
					public Perumahan mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Perumahan room = new Perumahan();
						room.setId_proyek(rs.getString("id_proyek"));
						room.setId_perumahan(rs.getString("id_perumahan"));
						room.setNama_perumahan(rs.getString("nama_perumahan"));
						room.setTipe_perumahan(rs.getString("tipe_perumahan"));
						room.setJumlah_unit(rs.getInt("jumlah_unit"));
						return room;
					}
				});

		PerumahanListVO cl = new PerumahanListVO();
		if (count > 0) {
			cl.setPagesCount((int) ceil((double) count / maxResults));
		}

		cl.setTotalPerumahans(count);
		cl.setPerumahans(listContact);
		return cl;	
	}

	private long CountMasterPerumahanByName(String name) {
		String sql = "select count(*) from perumahan where upper(nama_perumahan) like upper(?)  ";
		Integer r = this.jdbcTemplate.queryForObject(sql , new Object[]{name+"%"},Integer.class);
		return r;	
	}

	public void updatePerumahan(Perumahan contact) {
		String sql = "update perumahan set nama_perumahan = ?, tipe_perumahan = ? , jumlah_unit= ?  where  id_proyek = ? and id_perumahan= ?";
		jdbcTemplate.update(sql, new Object[] { contact.getNama_perumahan(),contact.getTipe_perumahan(),contact.getJumlah_unit(),contact.getId_proyek(),contact.getId_perumahan() });				
	}

	@Secured("ROLE_ADMIN")
	public void deletePerumahan(String id_proyek, String id_perumahan) {
		String sql = "delete from perumahan where  id_proyek = ? and id_perumahan = ?";
		  jdbcTemplate.update(sql, new Object[] { id_proyek, id_perumahan});		
	}

	public KonsumenListVO getListAllKonsumen(int page, int maxResults) {
		String sql = "select * from konsumen  limit " + maxResults + " offset "
				+ (page * maxResults);
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		List<Konsumen> rl = new ArrayList<Konsumen>();
		for (Map row : rows) {
			Konsumen r = new Konsumen();
			r.setNoktp((String) row.get("noktp"));
			r.setNama((String) row.get("nama"));
			r.setAlamat((String) row.get("alamat"));
			r.setNoakad((String) row.get("noakad"));
			r.setNohp((String) row.get("nohp"));
			r.setNoktp_pasangan((String) row.get("noktp_pasangan"));
			r.setNama_pasangan((String) row.get("nama_pasangan"));
			r.setNohp_pasangan((String) row.get("nohp_pasangan"));
			rl.add(r);
		}

		KonsumenListVO lstKonsumen = new KonsumenListVO();
		lstKonsumen.setKonsumens(rl);
		long totkonsumen = countMasterKonsumen();
		if (totkonsumen > 0) {
			lstKonsumen.setPagesCount((int) ceil((double) totkonsumen / maxResults));
		}
		lstKonsumen.setTotalKonsumens(totkonsumen);
		return lstKonsumen;
	}

	private long countMasterKonsumen() {
		Integer r = this.jdbcTemplate.queryForObject("select count(*) from konsumen", Integer.class);
		return r;
	}

	public void insertKonsumen(Konsumen contact) {
		String sql = "INSERT INTO konsumen "
				+ "(noktp, nama, alamat, noakad, nohp, noktp_pasangan, nama_pasangan, nohp_pasangan, alamat_pasangan) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
		Connection conn = null;

		try {
			conn = this.jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, contact.getNoktp());
			ps.setString(2, contact.getNama());
			ps.setString(3, contact.getAlamat());
			ps.setString(4, contact.getNoakad());
			ps.setString(5, contact.getNohp());
			ps.setString(6, contact.getNoktp_pasangan());
			ps.setString(7, contact.getNama_pasangan());
			ps.setString(8, contact.getNohp_pasangan());
			ps.setString(9, contact.getAlamat_pasangan());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void updateKonsumen(Konsumen contact) {
		String sql = "update konsumen set nama = ?, alamat = ? , nohp= ?, noktp_pasangan= ?, nama_pasangan=?, nohp_pasangan= ? where  noktp = ? and noakad= ?";
		jdbcTemplate.update(sql, new Object[] { contact.getNama(),contact.getAlamat(),contact.getNohp(),contact.getNoktp_pasangan(),
				contact.getNama_pasangan(), contact.getNohp_pasangan(), contact.getNoktp(), contact.getNoakad() });		
	}

	@Secured("ROLE_ADMIN")
	public void deleteKonsumen(String noktp) {
		String sql = "delete from konsumen where  noktp = ? ";
		jdbcTemplate.update(sql, new Object[] {noktp});		
	}

	public KonsumenListVO findKonsumenByNameLike(int page, int maxResults,
			String name) {
		long count = CountMasterKonsumenByName(name);
		String qu = "select * from konsumen where upper(nama) like upper(?) limit "
				+ maxResults + " offset " + (page * maxResults);
		List<Konsumen> listContact = jdbcTemplate.query(qu,new Object[]{name+"%"},
				new RowMapper<Konsumen>() {
					public Konsumen mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Konsumen r = new Konsumen();
						r.setNoktp(rs.getString("noktp"));
						r.setNama(rs.getString("nama"));
						r.setAlamat(rs.getString("alamat"));
						r.setNoakad(rs.getString("noakad"));
						r.setNohp(rs.getString("nohp"));
						r.setNoktp_pasangan(rs.getString("noktp_pasangan"));
						r.setNama_pasangan(rs.getString("nama_pasangan"));
						r.setNohp_pasangan(rs.getString("nohp_pasangan"));						
						return r;
					}
				});

		KonsumenListVO cl = new KonsumenListVO();
		if (count > 0) {
			cl.setPagesCount((int) ceil((double) count / maxResults));
		}

		cl.setTotalKonsumens(count);
		cl.setKonsumens(listContact);
		return cl;
	}

	private long CountMasterKonsumenByName(String name) {
		String sql = "select count(*) from konsumen where upper(nama) like upper(?)  ";
		Integer r = this.jdbcTemplate.queryForObject(sql , new Object[]{name+"%"},Integer.class);
		return r;

	}

	public List<ModelBayar> getListModelBayar() {		
		String	sql ="select * from model_bayar";
		List<ModelBayar> 	listCaraBayar = jdbcTemplate.query(sql, 
					new RowMapper<ModelBayar>() {
						public ModelBayar mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							ModelBayar room = new ModelBayar();
							room.setKode_bayar(rs.getInt("kode_bayar"));
							room.setKeterangan(rs.getString("keterangan"));							 
							return room;
						}
					});
		return listCaraBayar;
	}

	public List<ModelBayar> getListModelBayarDP() {		
		String	sql ="select * from model_bayardp";
		List<ModelBayar> 	listCaraBayar = jdbcTemplate.query(sql, 
					new RowMapper<ModelBayar>() {
						public ModelBayar mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							ModelBayar room = new ModelBayar();
							room.setKode_bayar(rs.getInt("kode_bayar"));
							room.setKeterangan(rs.getString("keterangan"));							 
							return room;
						}
					});
		return listCaraBayar;
	}
	public List<Tenor> getListTenor(int kodebayar) {		
		String	sql ="select * from tenor where kode_bayar = ?";
		List<Tenor> listTenor= jdbcTemplate.query(sql,  new Object[]{kodebayar},
					new RowMapper<Tenor>() {
						public Tenor mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Tenor room = new Tenor();
							room.setKode_bayar(rs.getInt("kode_bayar"));
							room.setKode_tenor(rs.getInt("kode_tenor"));
							room.setKeterangan(rs.getString("keterangan"));							 
							return room;
						}
					});
		return listTenor;
	}

	public List<Tenor> getListTenorDP(int kodebayar) {		
		String	sql ="select * from tenordp where kode_bayar = ?";
		List<Tenor> listTenor= jdbcTemplate.query(sql,  new Object[]{kodebayar},
					new RowMapper<Tenor>() {
						public Tenor mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Tenor room = new Tenor();
							room.setKode_bayar(rs.getInt("kode_bayar"));
							room.setKode_tenor(rs.getInt("kode_tenor"));
							room.setKeterangan(rs.getString("keterangan"));							 
							return room;
						}
					});
		return listTenor;
	}
	public DeveloperListVO getListAllDeveloper(int page, int maxResults) {
		String sql = "select * from developer  limit " + maxResults + " offset "
				+ (page * maxResults);
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		List<Developer> rl = new ArrayList<Developer>();
		for (Map row : rows) {
			Developer r = new Developer();
			r.setId((int) row.get("id"));
			r.setNama((String) row.get("nama"));
			r.setAlamat((String) row.get("alamat"));
			r.setNpwp((String) row.get("npwp"));
			r.setNotelp((String) row.get("notelp"));
			rl.add(r);
		}

		DeveloperListVO lstDeve= new DeveloperListVO();
		lstDeve.setDeveloper(rl);
		long totDeve = countMasterDeveloper();
		if (totDeve > 0) {
			lstDeve.setPagesCount((int) ceil((double) totDeve / maxResults));
		}
		lstDeve.setTotalDeveloper(totDeve);
		return lstDeve;
	}
	private long countMasterDeveloper() {
		Integer r = this.jdbcTemplate.queryForObject("select count(*) from developer", Integer.class);
		return r;
	}

	public void insertDeveloper(Developer contact) {
		String sql = "insert into developer(nama, alamat, npwp, notelp) values(?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] { contact.getNama(),contact.getAlamat(),contact.getNpwp(),contact.getNotelp()});
		
	}

	public void updateDeveloper(Developer contact) {
		String sql = "update developer set nama =? , alamat =?,  npwp=?, notelp=? where id=?";
		int r = jdbcTemplate.update(sql, new Object[] { contact.getNama(),contact.getAlamat(),contact.getNpwp(),contact.getNotelp(), contact.getId()});
		
	}

	@Secured("ROLE_ADMIN")
	public void deleteDeveloper(int id) {
		String sql = "delete from developer  where id=?";
		int r = jdbcTemplate.update(sql, new Object[] { id });
		
	}

	public DeveloperListVO findDeveloperByNameLike(int page, int maxResults, String name) {
			long count = CountMasterDeveloperByName(name);
	String qu = "select * from developer where upper(nama) like upper(?) limit "
			+ maxResults + " offset " + (page * maxResults);
	List<Developer> listContact = jdbcTemplate.query(qu,new Object[]{name+"%"},
			new RowMapper<Developer>() {
				public Developer mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					Developer r = new Developer();
					r.setNama(rs.getString("nama"));
					r.setAlamat(rs.getString("alamat"));
					r.setNpwp(rs.getString("npwp"));
					r.setNotelp(rs.getString("notelp"));
					return r;
				}
			});

	DeveloperListVO cl = new DeveloperListVO();
	if (count > 0) {
		cl.setPagesCount((int) ceil((double) count / maxResults));
	}

	cl.setTotalDeveloper(count);
	cl.setDeveloper(listContact);
	return cl;
	}

	private long CountMasterDeveloperByName(String name) {
		String sql = "select count(*) from developer where upper(nama) like upper(?)  ";
		Integer r = this.jdbcTemplate.queryForObject(sql , new Object[]{name+"%"},Integer.class);
		return r;

	}

	public List<Developer> getListDeveloper() {
		String	sql ="select * from developer";
		List<Developer> 	listCaraBayar = jdbcTemplate.query(sql, 
					new RowMapper<Developer>() {
						public Developer mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Developer room = new Developer();
							room.setId(rs.getInt("id"));
							room.setNama(rs.getString("nama"));							 
							return room;
						}
					});
		return listCaraBayar;	
	}

	public ProdukListVO getListAllProduk(int page, int maxResults) {
		String sql = "select * from produk limit " + maxResults + " offset "
				+ (page * maxResults);
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		List<Produk> rl = new ArrayList<Produk>();
		for (Map row : rows) {
			Produk r = new Produk();
			r.setId_developer((int) row.get("id_developer"));
			r.setId_produk((int) row.get("id_produk"));
			r.setDeveloper((String) row.get("developer"));
			r.setProyek((String) row.get("proyek"));
			r.setId_proyek((int) row.get("id_proyek"));
			r.setId_kategori((String) row.get("kategori"));
			r.setTipe_rumah((String) row.get("tipe_rumah"));
			r.setLuas_tanah((String) row.get("luas_tanah"));
			r.setBlok((String) row.get("blok"));
			r.setNounit((String) row.get("nounit"));
			rl.add(r);
		}

		ProdukListVO lstProyek = new ProdukListVO();
		lstProyek.setProduks(rl);
		long totproyek = countMasterProduk();
		if (totproyek > 0) {
			lstProyek
					.setPagesCount((int) ceil((double) totproyek / maxResults));
		}
		lstProyek.setTotalProduks(totproyek);
		return lstProyek;
	}

	private long countMasterProduk() {
		Integer r = this.jdbcTemplate.queryForObject("select count(*) from produk", Integer.class);
		return r;
	}

	public List<DummyMaster> getListDummyProyekByDeveloper(int id_developer) {
		String	sql ="select * from proyek where id_developer=?";
		List<DummyMaster> 	listCaraBayar = jdbcTemplate.query(sql,new Object[]{id_developer}, 
					new RowMapper<DummyMaster>() {
						public DummyMaster mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							DummyMaster room = new DummyMaster();
							room.setKode(rs.getString("id_proyek"));
							room.setNama(rs.getString("nama_proyek"));							 
							return room;
						}
					});
		return listCaraBayar;
	}

	public java.util.List<Kategori> getListKategori() {
		String	sql ="select * from kategori ";
		List<Kategori> 	listCaraBayar = jdbcTemplate.query(sql, 
					new RowMapper<Kategori>() {
						public Kategori mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Kategori room = new Kategori();
							room.setId(rs.getString("id"));
							room.setNama(rs.getString("nama"));							 
							return room;
						}
					});
		return listCaraBayar;
	}

	public java.util.List<Tipe> getListTipe(String id) {
		String	sql ="select * from tipe where id_kategori=?";
		List<Tipe> 	listCaraBayar = jdbcTemplate.query(sql, new Object[]{id},
					new RowMapper<Tipe>() {
						public Tipe mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Tipe room = new Tipe();
							room.setId(rs.getString("id"));
							room.setNama(rs.getString("nama"));							 
							return room;
						}
					});
		return listCaraBayar;
	}

	public void insertProduk(Produk contact) {		 
			String sql = "INSERT INTO produk "
					+ "(id_developer,id_proyek, id_produk, kategori, tipe_rumah, luas_tanah, blok, nounit, developer,proyek) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Connection conn = null;

			try {
				conn = this.jdbcTemplate.getDataSource().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, contact.getId_developer());
				ps.setInt(2, contact.getId_proyek());
				ps.setInt(3, contact.getId_produk());
				ps.setString(4, contact.getId_kategori());
				ps.setString(5, contact.getTipe_rumah());
				ps.setString(6, contact.getLuas_tanah());
				ps.setString(7, contact.getBlok());
				ps.setString(8, contact.getNounit());
				ps.setString(9, getDeveloperName(contact.getId_developer()));
				ps.setString(10, getProyekName(contact.getId_proyek()));
				ps.executeUpdate();
				ps.close();

			} catch (SQLException e) {
				throw new RuntimeException(e);

			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
		}

	private String getDeveloperName(int id) {
		String r = this.jdbcTemplate.queryForObject("select nama from developer where id= ?",new Object[] { id }, String.class);
		return r;
	}
	private String getProyekName(int id) {
		String r = this.jdbcTemplate.queryForObject("select nama_proyek from proyek where id_proyek= ?",new Object[] { id }, String.class);
		return r;
	}

	public ProdukListVO findProdukByNameLike(int page, int maxResults,
			String name) {
		long count = CountMasterProdukByName(name);
		String qu = "select * from produk where upper(tipe_rumah) like upper(?) limit "
				+ maxResults + " offset " + (page * maxResults);
		List<Produk> listContact = jdbcTemplate.query(qu,new Object[]{name+"%"},
				new RowMapper<Produk>() {
					public Produk mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Produk room = new Produk();
						room.setId_developer(rs.getInt("id_developer"));
						room.setId_proyek(rs.getInt("id_proyek"));
						room.setId_produk(rs.getInt("id_produk"));
						room.setId_kategori(rs.getString("kategori"));
						room.setTipe_rumah(rs.getString("tipe_rumah"));
						room.setLuas_tanah(rs.getString("luas_tanah"));
						room.setBlok(rs.getString("blok"));
						room.setNounit(rs.getString("nounit"));
						return room;
					}
				});

		ProdukListVO cl = new ProdukListVO();
		if (count > 0) {
			cl.setPagesCount((int) ceil((double) count / maxResults));
		}

		cl.setTotalProduks(count);
		cl.setProduks(listContact);
		return cl;
	}

	public long CountMasterProdukByName(String name) {
		String sql = "select count(*) from produk where upper(tipe_rumah) like upper(?)  ";
		Integer r = this.jdbcTemplate.queryForObject(sql , new Object[]{name+"%"},Integer.class);
		return r;
	}

	@Secured("ROLE_ADMIN")
	public void deleteProduk(int id_produk) {
		  String sql = "delete from produk where  id_produk = ?";
		  jdbcTemplate.update(sql, new Object[] { id_produk});
	}

	public void updateProduk(Produk contact) {
		String sql = "update produk set id_developer = ?, id_proyek = ? , kategori=? , tipe_rumah= ?, luas_tanah=?, blok=?, nounit=?  where   id_produk= ?";
		jdbcTemplate.update(sql, new Object[] { 
				contact.getId_developer(),
				contact.getId_proyek(),
				contact.getId_kategori(),
				contact.getTipe_rumah(),
				contact.getLuas_tanah(),
				contact.getBlok(),
				contact.getNounit(),
				contact.getId_produk()
				});		
	}

  

	public ProdukListVO getListAvailProduk(int page, int maxResults) {
		String sql = "select * from produk where is_jual=0 limit " + maxResults + " offset "
				+ (page * maxResults);
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		List<Produk> rl = new ArrayList<Produk>();
		for (Map row : rows) {
			Produk r = new Produk();
			r.setId_developer((int) row.get("id_developer"));
			r.setId_produk((int) row.get("id_produk"));
			r.setDeveloper((String) row.get("developer"));
			r.setProyek((String) row.get("proyek"));
			r.setId_proyek((int) row.get("id_proyek"));
			r.setId_kategori((String) row.get("kategori"));
			r.setTipe_rumah((String) row.get("tipe_rumah"));
			r.setLuas_tanah((String) row.get("luas_tanah"));
			r.setBlok((String) row.get("blok"));
			r.setNounit((String) row.get("nounit"));
			rl.add(r);
		}

		ProdukListVO lstProyek = new ProdukListVO();
		lstProyek.setProduks(rl);
		long totproyek = countProdukAvail();
		if (totproyek > 0) {
			lstProyek
					.setPagesCount((int) ceil((double) totproyek / maxResults));
		}
		lstProyek.setTotalProduks(totproyek);
		return lstProyek;
	}

	private long countProdukAvail() {
		Integer r = this.jdbcTemplate.queryForObject("select count(*) from produk where is_jual=0", Integer.class);
		return r;
	}	 
	
	private int getAutoIncrement(String table) {
		Integer r = this.jdbcTemplate.queryForObject("SELECT auto_increment FROM INFORMATION_SCHEMA.TABLES WHERE table_name = ? ", new Object[]{table},Integer.class);
		return r;
	}	 
	
	public int insertTransaksi(Transaksi transaksi) throws SQLException {
		String sql = "INSERT INTO transaksi "
				+ "(harga_kesepakatan, total_penambahan, harga_plus_penambahan, dp, harga_total,"
				+ "luas_tanah, harga_permeter, harga_tanah, penambahan_lain, harga_lain, model_bayar, tenor, harga_appraisal, cicilan_perbulan, tgl_jatuhtempo,"
				+ "noktp, nama, alamat, id_produk, nama_perumahan, kategori, tipe_rumah, blok, nounit, model_bayardp, tenordp,cicilan_perbulandp,idtransaksi,noppjb) "  
				+ " VALUES (?, ?, ?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?) ";
		Connection conn = null;
		int r =0;
		try {
			conn = this.jdbcTemplate.getDataSource().getConnection();
			 conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, transaksi.getHarga_kesepakatan().toString());
			ps.setString(2, transaksi.getTotal_penambahan().toString());
			ps.setString(3, transaksi.getTotalHargaPlusPenambahan().toString());
			ps.setString(4, transaksi.getDp().toString());
			ps.setString(5, transaksi.getHarga_total().toString());
			ps.setString(6, transaksi.getLuas_tanah().toString());
			ps.setString(7, transaksi.getHarga_permeter().toString());
			ps.setString(8, transaksi.getHarga_tanah().toString());
			ps.setString(9, transaksi.getPenambahan_lain().toString());
			ps.setString(10, transaksi.getHarga_lain().toString());
			ps.setInt(11, transaksi.getModel_bayar());
			ps.setInt(12, transaksi.getTenor());
			ps.setString(13, "0");
			ps.setString(14, transaksi.getCicilan_perbulan().toString());
			ps.setInt(15, transaksi.getTgl_jatuhtempo());
			ps.setString(16, transaksi.getNoktp());
			ps.setString(17, transaksi.getNama());
			ps.setString(18, transaksi.getAlamat());
			ps.setInt(19, transaksi.getId_produk());
			ps.setString(20, transaksi.getNama_rumah());
			ps.setString(21, transaksi.getKategori());
			ps.setString(22, transaksi.getTipe_rumah());
			ps.setString(23, transaksi.getBlok());
			ps.setString(24, transaksi.getNounit());
			ps.setInt(25, transaksi.getModel_bayardp());
			ps.setInt(26, transaksi.getTenordp());
			ps.setString(27, transaksi.getCicilan_perbulandp().toString());
			ps.setString(28, transaksi.getNoppjb());
			int no = getAutoIncrement("transaksi");
			ps.setInt(28, no);
			r = ps.executeUpdate();
			ps.close();
			
			sql = "update produk set is_jual=? where id_produk=?";
			r = jdbcTemplate.update(sql, new Object[] {1, transaksi.getId_produk()});

			sql="insert into pembayaran(idtransaksi,angsuranke,noktp,id_produk,sisa_angsuran) "+
			    "values(?,?,?,?,?) ";
			r = jdbcTemplate.update(sql, new Object[] {
					no,
					1,
					transaksi.getNoktp(),
					transaksi.getId_produk(),
					transaksi.getHarga_total()
				});			 
			
			sql="insert into pembayaran_dp(idtransaksi,angsuranke,noktp,id_produk,sisa_dp) "+
				    "values(?,?,?,?,?) ";
				r = jdbcTemplate.update(sql, new Object[] {
						no,
						1,
						transaksi.getNoktp(),
						transaksi.getId_produk(),
						transaksi.getDp()
					});	
			 conn.commit();
		} catch (SQLException e) {
			 conn.rollback();
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return r;
	}

	public TransaksiListVO getListTransaksi(int lunas, int page, int maxResults) {
		String sql = "select * from transaksi where lunas= "+lunas+" order by idtransaksi desc limit " + maxResults + " offset "
				+ (page * maxResults);
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		List<Transaksi> rl = new ArrayList<Transaksi>();
		for (Map row : rows) {
			Transaksi r = new Transaksi();		
			r.setIdtransaksi((int) row.get("idtransaksi"));
			r.setId_produk((int) row.get("id_produk"));			
			r.setNoktp((String) row.get("noktp"));
			r.setNama((String) row.get("nama"));
			r.setAlamat((String) row.get("alamat"));
			r.setNama_rumah((String) row.get("nama_perumahan"));			
			r.setKategori((String) row.get("kategori"));
			r.setTipe_rumah((String) row.get("tipe_rumah"));
			r.setLuas_tanah((String) row.get("luas_tanah"));
			r.setBlok((String) row.get("blok"));
			r.setNounit((String) row.get("nounit"));
			r.setHarga_kesepakatan(new BigDecimal( row.get("harga_kesepakatan").toString()));
			r.setDp(new BigDecimal( row.get("dp").toString()));
			r.setCicilan_perbulan(row.get("cicilan_perbulan") ==null ? new BigDecimal(0):new BigDecimal( row.get("cicilan_perbulan").toString()));
			r.setCicilan_perbulandp(row.get("cicilan_perbulandp") ==null ? new BigDecimal(0):new BigDecimal( row.get("cicilan_perbulandp").toString()));
			r.setModel_bayar((int) row.get("model_bayar"));
			r.setTenor((int) row.get("tenor"));
			r.setTgl_jatuhtempo((int) row.get("tgl_jatuhtempo"));
			rl.add(r);
		}

		TransaksiListVO lstProyek = new TransaksiListVO();
		lstProyek.setTransaksis(rl);
		long totproyek = countListTransaksi(lunas);
		if (totproyek > 0) {
			lstProyek
					.setPagesCount((int) ceil((double) totproyek / maxResults));
		}
		lstProyek.setTotalTransaksis(totproyek);
		return lstProyek;
	}

	private long countListTransaksi(int lunas) {
		String sql = "select count(*) from transaksi where lunas= ?  ";
		Integer r = this.jdbcTemplate.queryForObject(sql , new Object[]{lunas},Integer.class);
		return r;
	}

	public TransaksiListVO getListTransaksiDP(int lunas, int page, int maxResults) {
		String sql = "select * from transaksi where lunas_dp= "+lunas+" limit " + maxResults + " offset "
				+ (page * maxResults);
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		List<Transaksi> rl = new ArrayList<Transaksi>();
		for (Map row : rows) {
			Transaksi r = new Transaksi();		
			r.setIdtransaksi((int) row.get("idtransaksi"));
			r.setId_produk((int) row.get("id_produk"));			
			r.setNoktp((String) row.get("noktp"));
			r.setNama((String) row.get("nama"));
			r.setAlamat((String) row.get("alamat"));
			r.setNama_rumah((String) row.get("nama_perumahan"));			
			r.setKategori((String) row.get("kategori"));
			r.setTipe_rumah((String) row.get("tipe_rumah"));
			r.setLuas_tanah((String) row.get("luas_tanah"));
			r.setBlok((String) row.get("blok"));
			r.setNounit((String) row.get("nounit"));
			r.setHarga_kesepakatan(new BigDecimal( row.get("harga_kesepakatan").toString()));
			r.setDp(new BigDecimal( row.get("dp").toString()));
			r.setCicilan_perbulan(new BigDecimal( row.get("cicilan_perbulan").toString()));
			r.setModel_bayar((int) row.get("model_bayar"));
			r.setTenor((int) row.get("tenor"));
			r.setTgl_jatuhtempo((int) row.get("tgl_jatuhtempo"));
			rl.add(r);
		}

		TransaksiListVO lstProyek = new TransaksiListVO();
		lstProyek.setTransaksis(rl);
		long totproyek = countListTransaksiDP(lunas);
		if (totproyek > 0) {
			lstProyek
					.setPagesCount((int) ceil((double) totproyek / maxResults));
		}
		lstProyek.setTotalTransaksis(totproyek);
		return lstProyek;
	}

	private long countListTransaksiDP(int lunas) {
		String sql = "select count(*) from transaksi where lunas_dp= ?  ";
		Integer r = this.jdbcTemplate.queryForObject(sql , new Object[]{lunas},Integer.class);
		return r;
	}
	
	public int UpdateAngsuran(Pembayaran transaksi) throws SQLException {
		Connection conn = null;
		int r =0;
		try {
			Pembayaran x= getSisaAngsuran(transaksi.getId_produk());
			
			conn = this.jdbcTemplate.getDataSource().getConnection();
			 conn.setAutoCommit(false);
			String sql = "update pembayaran set jumlah_bayar=? , tgl_bayar =?, sisa_angsuran = sisa_angsuran - ? where id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setBigDecimal(1, transaksi.getJumlah_bayar());
			ps.setString(2, transaksi.getTanggal_bayar());
			ps.setBigDecimal(3, transaksi.getJumlah_bayar());
			ps.setInt(4, x.getId());
			r = ps.executeUpdate();
			ps.close();			
			if (x.getSisa().subtract(transaksi.getJumlah_bayar()).compareTo(BigDecimal.ZERO) > 0){
				
				sql="insert into pembayaran(angsuranke,noktp,id_produk,sisa_angsuran,idtransaksi) "+
						"values(?,?,?,?,?) ";
				PreparedStatement ps2 = conn.prepareStatement(sql);			
				ps2.setLong(1, x.getAngsuranke()+1);
				ps2.setString(2, transaksi.getNoktp());
				ps2.setLong(3, transaksi.getId_produk());
				ps2.setBigDecimal(4, x.getSisa().subtract(transaksi.getJumlah_bayar()));
				ps2.setInt(5, transaksi.getIdtransaksi() );
				r = ps2.executeUpdate();
				ps2.close();			
				/*r = jdbcTemplate.update(sql, new Object[] {
					x.getAngsuranke()+1,
					transaksi.getNoktp(),
					transaksi.getId_produk(),
					x.getSisa()
				});*/			 
			}
			else {
				sql = "update transaksi set lunas=? where id_produk=?";
				r = jdbcTemplate.update(sql, new Object[] {1, transaksi.getId_produk()});
			}
			 conn.commit();
		} catch (SQLException e) {
			 conn.rollback();
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return r;
	}

	private Pembayaran getSisaAngsuran(int id_produk) {		 

        String sql = "select id, angsuranke, sisa_angsuran from pembayaran where id_produk= ?  "
        		     +" and angsuranke = (select max(angsuranke) from pembayaran where id_produk= ? )";
        Connection conn = null;
        try {
 
        	conn = this.jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setInt(1, id_produk);
            ps.setInt(2, id_produk);
            Pembayaran p = null;
 
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) { 
                p = new Pembayaran();
                p.setId(rs.getInt("id"));
                p.setAngsuranke(rs.getInt("angsuranke")); 
                p.setSisa(rs.getBigDecimal("sisa_angsuran"));    
               
            }
            rs.close();
            ps.close();

            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                conn.close();
                } catch (SQLException e) {}
            }
        }			 
	}

	public int UpdateAngsuranDP(Pembayaran transaksi) throws SQLException {
		Connection conn = null;
		int r =0;
		try {
			Pembayaran x= getSisaAngsuranDP(transaksi.getId_produk());
			
			conn = this.jdbcTemplate.getDataSource().getConnection();
			 conn.setAutoCommit(false);
			String sql = "update pembayaran_dp set jumlah_bayar=? , tgl_bayar =?, sisa_dp = sisa_dp - ? where id = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setBigDecimal(1, transaksi.getJumlah_bayar());
			ps.setString(2, transaksi.getTanggal_bayar());
			ps.setBigDecimal(3, transaksi.getJumlah_bayar());
			ps.setInt(4, x.getId());
			r = ps.executeUpdate();
			ps.close();			
			if (x.getSisa().subtract(transaksi.getJumlah_bayar()).compareTo(BigDecimal.ZERO) > 0){
				
				sql="insert into pembayaran_dp(angsuranke,noktp,id_produk,sisa_dp,idtransaksi) "+
						"values(?,?,?,?,?) ";
				PreparedStatement ps2 = conn.prepareStatement(sql);			
				ps2.setLong(1, x.getAngsuranke()+1);
				ps2.setString(2, transaksi.getNoktp());
				ps2.setLong(3, transaksi.getId_produk());
				ps2.setBigDecimal(4, x.getSisa().subtract(transaksi.getJumlah_bayar()));
				ps2.setInt(5, transaksi.getIdtransaksi() );
				r = ps2.executeUpdate();
				ps2.close();			
				/*r = jdbcTemplate.update(sql, new Object[] {
					x.getAngsuranke()+1,
					transaksi.getNoktp(),
					transaksi.getId_produk(),
					x.getSisa()
				});*/			 
			}
			else {
				sql = "update transaksi set lunas_dp=? where id_produk=?";
				r = jdbcTemplate.update(sql, new Object[] {1, transaksi.getId_produk()});
			}
			 conn.commit();
		} catch (SQLException e) {
			 conn.rollback();
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return r;
	}

	private Pembayaran getSisaAngsuranDP(int id_produk) {		 

        String sql = "select id, angsuranke, sisa_dp from pembayaran_dp where id_produk= ?  "
        		     +" and angsuranke = (select max(angsuranke) from pembayaran_dp where id_produk= ? )";
        Connection conn = null;
        try {
 
        	conn = this.jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql); 
            ps.setInt(1, id_produk);
            ps.setInt(2, id_produk);
            Pembayaran p = null;
 
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) { 
                p = new Pembayaran();
                p.setId(rs.getInt("id"));
                p.setAngsuranke(rs.getInt("angsuranke")); 
                p.setSisa(rs.getBigDecimal("sisa_dp"));    
               
            }
            rs.close();
            ps.close();

            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                conn.close();
                } catch (SQLException e) {}
            }
        }			 
	}
	
	public PembayaranListVO getListPembayaran(int page, int maxResults) {
		String sql = "select a.*, b.nama,b.alamat,b.nama_perumahan,b.blok,b.nounit  from pembayaran a, transaksi b where a.idtransaksi=b.idtransaksi order by a.idtransaksi, a.id limit " + maxResults + " offset "
				+ (page * maxResults);
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		List<Pembayaran> rl = new ArrayList<Pembayaran>();
		for (Map row : rows) {
			Transaksi t = new Transaksi();
			t.setNama((String) row.get("nama"));
			t.setAlamat((String) row.get("alamat"));
			t.setNama_rumah((String) row.get("nama_perumahan"));
			t.setBlok((String) row.get("blok"));
			t.setNounit((String) row.get("nounit"));
			Pembayaran r = new Pembayaran();
			r.setId((int) row.get("id"));
			r.setIdtransaksi((int) row.get("idtransaksi"));
			r.setAngsuranke((int) row.get("angsuranke"));
			r.setId_produk((int) row.get("id_produk"));			
			r.setNoktp((String) row.get("noktp"));
			r.setTanggal_bayar((String) row.get("tgl_bayar"));
			r.setJumlah_bayar(row.get("jumlah_bayar") == null ? new BigDecimal(0) : new BigDecimal( row.get("jumlah_bayar").toString()));
			r.setSisa(row.get("sisa_angsuran") == null ? new BigDecimal(0) : new BigDecimal( row.get("sisa_angsuran").toString()));
			r.setTransaksi(t);
			rl.add(r);
		}

		PembayaranListVO lstProyek = new PembayaranListVO();
		lstProyek.setPembayarans(rl);
		long totproyek = countListPembayaran();
		if (totproyek > 0) {
			lstProyek
					.setPagesCount((int) ceil((double) totproyek / maxResults));
		}
		lstProyek.setTotalPembayarans(totproyek);
		return lstProyek;
	}

	private long countListPembayaran() {
		String sql = "select count(*) from pembayaran a, transaksi b where a.idtransaksi=b.idtransaksi";
		Integer r = this.jdbcTemplate.queryForObject(sql ,  Integer.class);
		return r;
	}

	public PembayaranListVO getListPembayaranDP(int page, int maxResults) {
		String sql = "select a.*,b.nama,b.alamat,b.nama_perumahan,b.blok,b.nounit  from pembayaran_dp a, transaksi b where a.idtransaksi=b.idtransaksi order by a.idtransaksi, a.id limit " + maxResults + " offset "
				+ (page * maxResults);
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		List<Pembayaran> rl = new ArrayList<Pembayaran>();
		for (Map row : rows) {
			Transaksi t = new Transaksi();
			t.setNama((String) row.get("nama"));
			t.setAlamat((String) row.get("alamat"));
			t.setNama_rumah((String) row.get("nama_perumahan"));
			t.setBlok((String) row.get("blok"));
			t.setNounit((String) row.get("nounit"));
			Pembayaran r = new Pembayaran();			
			r.setAngsuranke((int) row.get("angsuranke"));
			r.setId_produk((int) row.get("id_produk"));			
			r.setNoktp((String) row.get("noktp"));
			r.setTanggal_bayar((String) row.get("tgl_bayar"));
			r.setJumlah_bayar(row.get("jumlah_bayar") == null ? new BigDecimal(0) : new BigDecimal( row.get("jumlah_bayar").toString()));
			r.setSisa(row.get("sisa_dp")==null ? new BigDecimal(0):new BigDecimal( row.get("sisa_dp").toString()));		
			r.setTransaksi(t);
			rl.add(r);
		}

		PembayaranListVO lstProyek = new PembayaranListVO();
		lstProyek.setPembayarans(rl);
		long totproyek = countListPembayaranDP();
		if (totproyek > 0) {
			lstProyek
					.setPagesCount((int) ceil((double) totproyek / maxResults));
		}
		lstProyek.setTotalPembayarans(totproyek);
		return lstProyek;
	}

	private long countListPembayaranDP() {
		String sql = "select count(*) from pembayaran_dp  ";
		Integer r = this.jdbcTemplate.queryForObject(sql ,  Integer.class);
		return r;
	}

	public RekapPiutangListVO getListRekapPiutang(int page, int maxResults) {
		String sql="select a.id_proyek, nama_perumahan, harga, angsuran, dp from ( "
						+" select id_proyek, nama_perumahan, SUM(harga_kesepakatan) as harga" 
						+" from transaksi a "
						+" inner join produk b on a.id_produk=b.id_produk "
						+" group by id_proyek, nama_perumahan "
						+" ) as a "
						+" inner join ("
						+" select id_proyek, SUM(jumlah_bayar) as angsuran" 
						+" from pembayaran a"
						+" inner join produk b on a.id_produk=b.id_produk"
						+" group by id_proyek "
						+" ) as b on a.id_proyek=b.id_proyek"
						+" inner join ("
						+" select id_proyek, SUM(jumlah_bayar) as dp" 
						+" from pembayaran_dp a"
						+" inner join produk b on a.id_produk=b.id_produk"
						+" group by id_proyek "
						+" ) as c on a.id_proyek=c.id_proyek limit " + maxResults + " offset "
				+ (page * maxResults);
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		List<Rekap_piutang> rl = new ArrayList<Rekap_piutang>();
		for (Map row : rows) {
			Rekap_piutang rp = new Rekap_piutang();
			
			Transaksi t = new Transaksi();
			t.setNama_rumah((String) row.get("nama_perumahan"));
			t.setId_produk((int) row.get("id_proyek"));
			t.setHarga_kesepakatan( new BigDecimal( row.get("harga").toString()));
			
			Pembayaran r = new Pembayaran();			
			r.setJumlah_bayar(row.get("angsuran") == null ? new BigDecimal(0) : new BigDecimal( row.get("angsuran").toString()));
			r.setTransaksi(t);
			
			Pembayaran rd= new Pembayaran();			
			rd.setJumlah_bayar(row.get("dp") == null ? new BigDecimal(0) : new BigDecimal( row.get("dp").toString()));
			rd.setTransaksi(t);

			rp.setPembayaran(r);
			rp.setPembayaran_dp(rd);
			
			rl.add(rp);
		}

		RekapPiutangListVO lstProyek = new RekapPiutangListVO();
		lstProyek.setRekap_piutangs(rl);
		long totproyek = countListRekapPiutang();
		if (totproyek > 0) {
			lstProyek
					.setPagesCount((int) ceil((double) totproyek / maxResults));
		}
		lstProyek.setTotalRekaps(totproyek);
		return lstProyek;
	}
	
	private long countListRekapPiutang() {
		String sql = "select count(*) from  ( "
						+" select id_proyek, nama_perumahan, SUM(harga_kesepakatan) as harga" 
						+" from transaksi a "
						+" inner join produk b on a.id_produk=b.id_produk "
						+" group by id_proyek, nama_perumahan "
						+" ) as a "
						+" inner join ("
						+" select id_proyek, SUM(jumlah_bayar) as angsuran" 
						+" from pembayaran a"
						+" inner join produk b on a.id_produk=b.id_produk"
						+" group by id_proyek "
						+" ) as b on a.id_proyek=b.id_proyek"
						+" inner join ("
						+" select id_proyek, SUM(jumlah_bayar) as dp" 
						+" from pembayaran_dp a"
						+" inner join produk b on a.id_produk=b.id_produk"
						+" group by id_proyek "
						+" ) as c on a.id_proyek=c.id_proyek";
		Integer r = this.jdbcTemplate.queryForObject(sql ,  Integer.class);
		return r;
	}

	public PembayaranListVO findPembayaranBy(int page, int maxResults,int searchBy, String name) {
		String sql = "select a.*, b.nama,b.alamat,b.nama_perumahan,b.blok,b.nounit  from pembayaran a, transaksi b where a.idtransaksi=b.idtransaksi and noppjb= ? order by a.idtransaksi, a.id limit " + maxResults + " offset "
				+ (page * maxResults);
		
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql, new Object[]{name} );
		List<Pembayaran> rl = new ArrayList<Pembayaran>();
		for (Map row : rows) {
			Transaksi t = new Transaksi();
			t.setNama((String) row.get("nama"));
			t.setAlamat((String) row.get("alamat"));
			t.setNama_rumah((String) row.get("nama_perumahan"));
			t.setBlok((String) row.get("blok"));
			t.setNounit((String) row.get("nounit"));
			Pembayaran r = new Pembayaran();
			r.setId((int) row.get("id"));
			r.setIdtransaksi((int) row.get("idtransaksi"));
			r.setAngsuranke((int) row.get("angsuranke"));
			r.setId_produk((int) row.get("id_produk"));			
			r.setNoktp((String) row.get("noktp"));
			r.setTanggal_bayar((String) row.get("tgl_bayar"));
			r.setJumlah_bayar(row.get("jumlah_bayar") == null ? new BigDecimal(0) : new BigDecimal( row.get("jumlah_bayar").toString()));
			r.setSisa(row.get("sisa_angsuran") == null ? new BigDecimal(0) : new BigDecimal( row.get("sisa_angsuran").toString()));
			r.setTransaksi(t);
			rl.add(r);
		}

		PembayaranListVO lstProyek = new PembayaranListVO();
		lstProyek.setPembayarans(rl);
		long totproyek = countListPembayaranBy( searchBy,  name);
		if (totproyek > 0) {
			lstProyek
					.setPagesCount((int) ceil((double) totproyek / maxResults));
		}
		lstProyek.setTotalPembayarans(totproyek);
		return lstProyek;
	}
	
	private long countListPembayaranBy(int searchBy, String name) {
		String sql = "select count(*) from pembayaran a, transaksi b where a.idtransaksi=b.idtransaksi and noppjb=?";
		Integer r = this.jdbcTemplate.queryForObject(sql ,  new Object[]{name} , Integer.class);
		return r;
	}
	

	public UserListVO getListAllUser(int page, int maxResults) {
		String sql = "select a.*,b.user_role_id, b.authority from users a, user_roles b where a.user_id=b.user_id limit " + maxResults + " offset "
				+ (page * maxResults);
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
		List<User> rl = new ArrayList<User>();
		for (Map row : rows) {
			User r = new User();			
			r.setName((String) row.get("user_name"));
			r.setPassword((String) row.get("password"));
			r.setRole_desciption((String) row.get("authority"));
			r.setRole_id((int) row.get("user_role_id"));
			r.setUserid((int) row.get("user_id"));			
			rl.add(r);
		}

		UserListVO lstProyek = new UserListVO();
		lstProyek.setUser(rl);
		int totproyek = countMasterUser();
		if (totproyek > 0) {
			lstProyek
					.setPagesCount((int) ceil((double) totproyek / maxResults));
		}
		lstProyek.setTotalUser(totproyek);
		return lstProyek;
	}

	private int countMasterUser() {
		String sql = "select count(*) from users";
		Integer r = this.jdbcTemplate.queryForObject(sql , Integer.class);
		return r;
	}

	public List<Roles>  getListRoles() {
		String	sql ="select * from roles";
		List<Roles> 	listCaraBayar = jdbcTemplate.query(sql, 
					new RowMapper<Roles>() {
						public Roles mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Roles room = new Roles();
							room.setRole_id(rs.getInt("role_id"));
							room.setRole_description(rs.getString("role_description"));							 
							
							return room;
						}
					});
		return listCaraBayar;
	}
	
	
}
 
