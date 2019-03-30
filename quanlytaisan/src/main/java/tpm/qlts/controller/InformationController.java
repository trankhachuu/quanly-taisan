package tpm.qlts.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tpm.qlts.custommodels.NhanVienUpdate;
import tpm.qlts.entitys.ChucVu;
import tpm.qlts.entitys.DonViTinh;
import tpm.qlts.entitys.NhanVien;
import tpm.qlts.entitys.PhongBan;
import tpm.qlts.entitys.TinhTrang;
import tpm.qlts.services.ChucVuService;
import tpm.qlts.services.DonViTinhService;
import tpm.qlts.services.NhanVienService;
import tpm.qlts.services.PhongBanService;
import tpm.qlts.services.TinhTrangService;

@Controller
@RestController
@RequestMapping("infor")
public class InformationController {
	@Autowired 
	private PhongBanService phongBanService;
	
	@Autowired
	private NhanVienService nhanVienService;
	
	@Autowired 
	private ChucVuService chucVuService;
	
	@Autowired
	private TinhTrangService tinhTrangService;
	
	@Autowired
	private DonViTinhService donViTinhService;
	
	@GetMapping(value="listAllPhongBan")
	public List<PhongBan> getPhongBan()
	{
		List<PhongBan> listPhongBan = phongBanService.findAll();
		return listPhongBan;
	}
	
	@PostMapping(value="add-phongban")
	public PhongBan addPhongBan(@RequestBody PhongBan phongBan)
	{
		PhongBan resPhongBan = phongBanService.update(phongBan);
		return resPhongBan;
	}
	
	@PutMapping("update-phongban")
	public PhongBan updatePhongBan(@RequestBody PhongBan phongBan)
	{
		if (phongBanService.existsById(phongBan.getMaPhongBan())) {
			return phongBanService.update(phongBan);
		}
		return null;
	}
	
	@DeleteMapping(value="delete-phongban/{id}")
	public void deletePhongBan(@PathVariable String id)
	{
		phongBanService.deleteById(id);
	}
	
	@DeleteMapping("delete-listPhongBan")
	public int deleteBylistPhongBan(@RequestBody List<String> lstID)
	{
		int count = 0;
		for (String id : lstID) {
			if (phongBanService.existsById(id)) {
				phongBanService.deleteById(id);
			}
			count ++;
		}
		return count;
	}
	
	@GetMapping(value="listAllChucVu")
	public List<ChucVu> getChucVu()
	{
		List<ChucVu> listChucVu = chucVuService.findAll();
		return listChucVu;
	}
	
	@PostMapping("add-ChucVu")
	public ChucVu addChucVu(@RequestBody ChucVu chucVu)
	{
		ChucVu resChucVu = chucVuService.update(chucVu);
		return resChucVu;
	}
	
	@GetMapping(value="listAllNhanVien")
	public List<NhanVien> getNhanVien()
	{
		List<NhanVien> listNhanVien = nhanVienService.findAll();
		return listNhanVien;
	}
	
	@PostMapping(value="add-employee")
	public NhanVienUpdate addEmployee(@RequestBody NhanVien nhanVien) { // vì sao lại có 2 cái trường đầu tiên
//		 vì trong entity nhân viên xét thuộc tính insertable và updatetable xét cho nó là false nên khi vào phải get cái trường đó vào
		nhanVien.setChucVu(new ChucVu(nhanVien.getMaChucVu()));
		nhanVien.setPhongBan(new PhongBan(nhanVien.getMaPhongBan()));
		NhanVien resNhanVien= nhanVienService.save(nhanVien);
		String tenChucVu = resNhanVien.getChucVu().getTenChucVu();
		String tenPhongBan = resNhanVien.getPhongBan().getTenPhongBan();
		
		return new NhanVienUpdate(resNhanVien.getMaNhanVien(), resNhanVien.getTenNhanVien(), resNhanVien.getNgaySinh(), resNhanVien.getQueQuan(), tenChucVu, tenPhongBan);
	}
	
	@RequestMapping(value = "update-employee", method = RequestMethod.PUT)
	public NhanVienUpdate updateNhanVien(@RequestBody NhanVien nhanVien)
	{
		nhanVien.setChucVu(new ChucVu(nhanVien.getMaChucVu()));
		nhanVien.setPhongBan(new PhongBan(nhanVien.getMaPhongBan()));
		if (nhanVienService.existsById(nhanVien.getMaNhanVien())) {
			NhanVien resNhanVien= nhanVienService.save(nhanVien);
			String tenChucVu = resNhanVien.getChucVu().getTenChucVu();
			String tenPhongBan = resNhanVien.getPhongBan().getTenPhongBan();
			return new NhanVienUpdate(resNhanVien.getMaNhanVien(), resNhanVien.getTenNhanVien(), resNhanVien.getNgaySinh(), resNhanVien.getQueQuan(), tenChucVu, tenPhongBan);
		}
		return null;
	}
	
	@DeleteMapping(value="delete-employee/{id}")
	public void deleteNhanVien(@PathVariable String id)
	{
		nhanVienService.deleteById(id);
	}
	
	@DeleteMapping("delete-by-listNhanVien")
	public int deleteByListNhanVien(@RequestBody List<String> lstID)
	{
		int cout = 0;
		for (String id : lstID) {
			if (nhanVienService.existsById(id)) {
				nhanVienService.deleteById(id);
				cout ++;
			}
		}
		return cout;
	}
	
	@GetMapping(value="list-employeeId/{id}")
	public NhanVien getNhanVienById(@PathVariable String id)
	{
		Optional<NhanVien> nhanVien = nhanVienService.findById(id);
		return nhanVien.get();
	}
	
	@GetMapping(value="list-employeeByName/{ten}")
	public List<NhanVien> listNhanVienByName(@PathVariable("ten") String ten)
	{
		return nhanVienService.findNhanVienByName(ten);
	}
	
	@GetMapping("list-tinhtrang")
	public List<TinhTrang> getTinhTrang()
	{
		List<TinhTrang> resTinhTrang = tinhTrangService.findAll();
		return resTinhTrang;
	}
	
	@PostMapping(value="add-tinhtrang")
	public TinhTrang addTinhTrang(@RequestBody TinhTrang tinhTrang)
	{
		TinhTrang resTinhTrang = tinhTrangService.update(tinhTrang);
		return resTinhTrang;
	}
	
	@PutMapping(value="update-tinhtrang")
	public TinhTrang updateTinhTrang(@RequestBody TinhTrang tinhTrang)
	{
		if (tinhTrangService.exitstsById(tinhTrang.getMaTinhTrang())) {
			TinhTrang resTinhTrang = tinhTrangService.update(tinhTrang);
			return resTinhTrang;
		}
		return null;
	}
	
	@DeleteMapping(value="delete-tinhtrang/{id}")
	public void deleteTinhTrang(@PathVariable String id)
	{
		tinhTrangService.deleteById(id);
	}
	
	@DeleteMapping("delete-by-list")
	public int deleteByList(@RequestBody List<String> lstID)
	{
		int cout = 0;
		for (String id : lstID) {
			if (tinhTrangService.exitstsById(id)) {
				tinhTrangService.deleteById(id);
				cout ++;
			}
		}
		return cout;
	}
	
//	@GetMapping(value="list-searchNhanVien/{key}")
//	public List<NhanVienUpdate> search(@RequestBody String key){
//		List<NhanVien> nvList = nhanVienService.findByKey(key);
//
//		List<NhanVienUpdate> resList = new ArrayList<>();
//		for(NhanVien nvItem : nvList){
//
//			resList.add(new NhanVienUpdate(nvItem.getTenNhanVien(), nvItem.getChucVu().getTenChucVu(), nvItem.getNgaySinh(), nvItem.getQueQuan(), nvItem.getPhongBan().getTenPhongBan(), key));
//		}
//		return resList;
//
//	}
	
	@RequestMapping(value="list-employeeUpdate")
	public List<NhanVienUpdate> getNhanVienUpdate()
	{
		List<NhanVien> nvList = nhanVienService.findAll();
		
		List<NhanVienUpdate> reslist = new ArrayList<>();
		for(NhanVien nvItem : nvList){

			reslist.add(new NhanVienUpdate(nvItem.getMaNhanVien(), nvItem.getTenNhanVien()
					, nvItem.getNgaySinh(), nvItem.getQueQuan(), nvItem.getChucVu().getTenChucVu(), 
					nvItem.getPhongBan().getTenPhongBan()));
		}
		return reslist;
	}
	////////////////////////----------Don vi tinh-----------------///////////////////////
	@GetMapping(value = "listAllDonViTinh")
	public List<DonViTinh> listDonViTinh()
	{
		return donViTinhService.findAll();
	}
	
	@PostMapping(value = "add-newdonvitinh")
	public DonViTinh addDonViTinh(@RequestBody DonViTinh donViTinh)
	{
		return donViTinhService.save(donViTinh);
	}
	
	@PutMapping(value = "update-donviinh")
	public DonViTinh updateDonViTinh(@RequestBody DonViTinh donViTinh)
	{
		if (donViTinhService.existsById(donViTinh.getMaDonViTinh())) {
			return donViTinhService.save(donViTinh);
		}
		return null;
	}
	
	@DeleteMapping(value = "delete-donvitinh/{id}")
	public void deleteDonviTinh(@PathVariable Integer id)
	{
		donViTinhService.deleteById(id);
	}
	
	@DeleteMapping(value = "delete-list-donvitinh")
	public int deleteByLstDonViTinh(@RequestBody List<Integer> lstID) {
		int count = 0;
		for (Integer id : lstID) {
			if (donViTinhService.existsById(id)) {
				donViTinhService.deleteById(id);
			}
			count ++;
		}
		return count;
	}
}