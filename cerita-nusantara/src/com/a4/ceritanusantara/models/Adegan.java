package com.a4.ceritanusantara.models;

public class Adegan extends SubCerita {

	private String [] dialogSumatera;
	private String [] dialogPemburu;
	private String [] dialogSiParkit;
	private String [] parkitMerah;
	private String [] parkitKuning;
	private String [] rajaAceh;
	private String [] pengawal;
	private String [] narasiSumatera;
	
	
	public Adegan(String nama, int tipe) {
		super(nama, tipe);
		dialogSumatera[0]="tes";
		
		// TODO Auto-generated constructor stub
		
	}
	
	public String [] getDialogSumatera() {
		dialogSumatera[0]="";
		return dialogSumatera;
	}
	
	public String [] getNarasiSumatera() {
		narasiSumatera[0]="Nanggroe Aceh Darussalam merupakan propinsi di Indonesia yang kaya, subur dan makmur. " +
				"Konon, di tengah hutan belantara itu, hiduplah sekawanan burung parakeet yang hidup damai, tenteram, dan makmur.";
		narasiSumatera[1]="Setiap hari mereka bernyanyi riang dengan suara merdu bersahut-sahutan dan saling membantu mencari makanan." +
				"Kawanan burung tersebut dipimpin oleh seorang raja parakeet yang bernama si Parkit.";
		narasiSumatera[2]=		
				"Namun, di tengah suasana bahagia itu, kedamaian mereka terusik oleh kedatangan seorang Pemburu." +
				"Ternyata, ia berniat menangkap dan menjual burung parakeet tersebut.";
		narasiSumatera[3]=
				"Pelan-pelan tapi pasti, si Pemburu itu melangkah ke arah kawanan burung parakeet itu, " +
				"lalu memasang perekat di sekitar sarang-sarangnya.";
		
		narasiSumatera[4]="Namun, karena harus mencari makan, burung-burung parakeet itu pun keluar dari sarangnya." +
				"Alhasil, apa yang ditakutkan burung-burung parakeet itu pun terjadi.";
		narasiSumatera[5]=
				"Bencana tak terelakkan, burung-burung parakeet itu terekat pada perekat si Pemburu." +
				"Mereka meronta-ronta untuk melepaskan diri dari perekat tersebut, namun usaha mereka sia-sia." +
				"Kawanan burung parakeet tersebut menjadi panik dan bingung, kecuali si Parkit, raja parakeet.";
		
		narasiSumatera[6]="Mendengar penjelasan raja Parakeet itu, rakyatnya terdiam." +
				"Sejenak, suasana menjadi hening. Di tengah keheningan itu ...";
		narasiSumatera[7]="Kini, rakyatnya sudah mengerti apa yang direncanakan oleh si Parkit." +
				"Mereka berjanji akan menuruti perintah rajanya.";
		
		narasiSumatera[8]="Keesokan harinya, si Pemburu pun datang. Dengan sangat hati-hati, " +
				"si Pemburu melepaskan burung parakeet tersebut satu persatu dari perekatnya.";
		
		narasiSumatera[9]="Dengan rasa kesal, si Pemburu berjalan seenaknya, tiba-tiba ia jatuh terpeleset.";
		narasiSumatera[10]="Kawanan burung parakeet yang berpura-pura mati di sekitarnya pun kaget dan terbang dengan seketika tanpa menunggu hitungan dari si Parkit." +
				"Si Pemburu pun berdiri kaget, karena ia merasa telah ditipu oleh kawanan burung parakeet itu.";
		narasiSumatera[11]="Namun, tiba-tiba ia tersenyum, karena melihat ada seekor burung parakeet yang masih melekat pada perekatnya." +
				"Lalu ia menghampiri burung parakeet tersebut, yang tidak lain adalah si Parkit.";
		narasiSumatera[12]="Si Parkit pun bernyanyi. Suara si Parkit yang merdu itu berhasil mumbujuk si Pemburu, sehingga ia tidak jadi dibunuh.";
		narasiSumatera[13]="Karena takut dibunuh, si Parkit pun setuju. Setelah itu, si Pemburu membawa si Parkit pulang.";
		narasiSumatera[14]="Sesampai di rumahnya, si Parkit tidak dikurung dalam sangkar, tapi salah satu kakinya diikat pada tiang yang cukup tinggi.";
		narasiSumatera[15]="Sejak saat itu, setiap hari si Parkit selalu bernyanyi untuk menghibur si Pemburu itu." +
				"Si Pemburu pun sangat senang mendengarkan suara si Parkit.";
		return narasiSumatera;
		
	}
	
}
