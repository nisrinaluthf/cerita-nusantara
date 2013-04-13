package com.a4.ceritanusantara.models;

public class Adegan extends SubCerita {
	private String [] dialogNusa;
	private String [] dialogAdik;
	private String [] dialogIstri;
	private String [] dialogSaluang;
	private String [] dialogJelawat;
	private String [] narasiKalimantan;
	
	private String [] dialogPemburu;
	private String [] dialogSiParkit;
	private String [] dialogParkit1;
	private String [] dialogParkit2;
	private String [] dialogRaja;
	private String [] dialogPengawal;
	private String [] narasiSumatera;
		
	public Adegan(String nama, int tipe) {
		super(nama, tipe);		
		// TODO Auto-generated constructor stub
		
	}
	
	public String [] getDialogNusa() {
		dialogNusa[0]="Aku akan pergi mencari makanan di tengah hutan itu. Kamu selesaikan saja pekerjaan itu.";
		return dialogNusa;
	}
	
	public String [] getDialogAdik() {
		dialogAdik[0]="Baik, Bang!";
		
		return dialogAdik;
	}
	
	public String [] getDialogIstri() {
		
		return dialogIstri;
	}
	
	public String [] getDialogSaluang() {
		
		return dialogSaluang;
	}
	
	public String [] getDialogJelawat() {
		
		return dialogJelawat;
	}
	
	public String [] getNarasiKalimantan() {
		narasiKalimantan[0]="Dahulu kala, hiduplah seorang laki-laki bernama Nusa. Ia tinggal bersama istri dan adik ipar laki-lakinya di sebuah kampung di pinggir Sungai Kahayan, Kalimantan Tengah.";
		narasiKalimantan[1]="Pekerjaan sehari-hari Nusa dan adik iparnya adalah bercocok tanam serta menangkap ikan di Sungai Kahayan.";
		narasiKalimantan[2]="Pada suatu waktu, kemarau panjang melanda daerah tempat tinggal mereka. Kelaparan terjadi di mana-mana. " +
				"Semua tanaman penduduk tidak dapat tumbuh dengan baik. Air Sungai Kahayan surut dan ikan-ikannya pun semakin berkurang.";
		narasiKalimantan[3]="Melihat kondisi itu, Nusa bersama istri dan adik iparnya memutuskan untuk pindah ke sebuah udik (dusun) dengan harapan akan mendapatkan sumber penghidupan yang lebih baik.";
		narasiKalimantan[4]="Setelah tiga hari menyusuri Sungai Rungan (anak Sungai Kahayan) dengan bekal seadanya, sampailah mereka di persimpangan sungai. " +
				"Namun, mereka tidak dapat melanjutkan perjalanan, karena ada sebatang pohon besar yang tumbang dan melintang di tengah sungai. " +
				"Untuk melintasi sungai itu, mereka harus memotong pohon itu. Akhirnya Nusa dan adik iparnya secara bergantian memotong pohon itu dengan menggunakan kapak.";
		narasiKalimantan[5]="Hingga sore, pohon itu belum juga terputus. Perut mereka pun sudah mulai keroncongan. Sementara bekal yang mereka bawa sudah habis. " +
				"Akhirnya, Nusa memutuskan untuk pergi mencari makanan ke hutan di sekitar sungai itu.";
		
		return narasiKalimantan;
	}
	
	public String [] getDialogPemburu() {
		dialogPemburu[0]="Ehm.. Aku akan kaya raya dengan menjual kalian!";
		dialogPemburu[1]="Wah.. kenapa burung-burung parakeet ini tidak bergerak? Jangan-jangan mereka semua telah mati.";
		dialogPemburu[2]="Sial! aku ditipu..!";
		dialogPemburu[3]="Kamu akan kubunuh!";
		dialogPemburu[4]="Enak saja! Kamu dan teman-temanmu telah menipuku. Kalau tidak, pasti aku sudah banyak menangkap kalian!";
		dialogPemburu[5]="Menghiburku?";
		dialogPemburu[6]="Memangnya suaramu bagus?";
		dialogPemburu[7]="Baiklah, aku tidak akan membunuhmu, tapi kamu harus bernyanyi setiap hari!";
		dialogPemburu[8]="Untung aku tidak membunuh burung parakeet itu.";
		dialogPemburu[9]="Ampun, Baginda! Hamba tidak bermaksud menentang keinginan Baginda!";
		dialogPemburu[10]="Ampun, Baginda! Mohon beribu ampun! Hamba sangat sayang pada burung tersebut. Selama ini hamba telah memeliharanya dengan baik";
		dialogPemburu[11]="Ampun, Baginda! Jika Baginda benar-benar menyukai burung parakeet tersebut, silakan kirim pengawal untuk mengambilnya!";
		
		return dialogPemburu;
	}

	public String [] getDialogSiParkit() {
		dialogSiParkit[0]="Tenang, Rakyatku! Ini adalah perekat yang dipasang si Pemburu, berarti dia ingin menangkap kita hidup-hidup.";
		dialogSiParkit[1]="Jadi, kalau kita mati, si Pemburu itu tidak akan mengambil kita.";
		dialogSiParkit[2]="Besok, ketika si Pemburu itu datang, kita pura-pura mati saja!";
		dialogSiParkit[3]="Besok, setelah si Pemburu melepaskan kita dari perekat yang dipasangnya, dia akan memeriksa kita satu per satu.";
		dialogSiParkit[4]="Bila dilihatnya kita telah mati, maka dia akan meninggalkan kita di sini.";
		dialogSiParkit[5]="Tunggu sampai hitunganku yang ke seratus agar kita dapat terbang secara bersama-sama!";
		dialogSiParkit[6]="Ampuni hamba, Tuan! Jangan bunuh hamba! Lepaskan hamba, Tuan!";
		dialogSiParkit[7]="Iya. Tapi itu kan bukan salahku. Ampuni hamba, Tuan! Hamba akan menghibur Tuan setiap hari!";
		dialogSiParkit[8]="Betul, Tuan. Hamba akan bernyanyi setiap hari untuk Tuan!";
		
		dialogSiParkit[9]="Bagaimana caranya ya....aku bisa keluar dari sangkar ini?";
		dialogSiParkit[10]="Aahh....aku harus berpura-pura mati lagi!";
		dialogSiParkit[11]="Aku bebaasss...!!! Aku bebaasss....!!!";
		return dialogSiParkit;
	}
	
	public String [] getDialogParkit1() {
		dialogParkit1[0]="Hati-hati! Pemburu itu telah memasang perekat di sekitar sarang kita!" +
				"Jangan sampai tertipu! Sebaiknya kita tidak terbang ke mana-mana dulu!";
		dialogParkit1[1]="Berpura-pura mati? Untuk apa?";
		return dialogParkit1;
	}
	
	public String [] getDialogParkit2() {
		dialogParkit2[0]="Ya, betul! Kita memang harus berhati-hati.";
		dialogParkit2[1]="Oh, begitu..!? Baiklah, besok kita akan berpura-pura mati agar dapat bebas dari Pemburu itu!";
		return dialogParkit2;
	}
	
	public String [] getDialogRaja() {
		dialogRaja[0]="Lalu, kenapa kamu tidak mau memberikan burung itu?";
		dialogRaja[1]="Kalau begitu, bagaimana jika kuganti dengan uang yang sangat banyak?";
		dialogRaja[2]="Kenapa burung parakeetku tidak mau bernyanyi lagi beberapa hari ini? Dia sakit, ya?";
		dialogRaja[3]="Ada apa, ya?";
		
		dialogRaja[4]="Siapkan upacara penguburan! Kuburkan burung parakeetku itu dengan baik!";
		
		return dialogRaja;
	}
	
	public String [] getDialogPengawal() {
		dialogPengawal[0]="Maaf, Tuanku. Hamba juga tidak tahu apa sebabnya. Saya telah memberinya makan seperti biasanya, tetapi tetap saja ia tidak mau bernyanyi.";
		dialogPengawal[1]="Ampun, Tuanku.";
		dialogPengawal[2]="Hamba sudah merawat dan memelihara sebaik mungkin, tapi burung parakeet ini tidak tertolong lagi, mungkin karena sudah tua.";
		
		dialogPengawal[3]=" Siap, Tuanku! Hamba laksanakan!";
		return dialogPengawal;
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
		
		narasiSumatera[16]="Sampai pada suatu hari, kemerduan suara si Parkit tersebut terdengar oleh Raja Aceh di istananya." +
				"Raja Aceh itu ingin agar burung parakeet itu menjadi miliknya.";
		narasiSumatera[17]="Sang Raja memanggil si Pemburu menghadap kepadanya.Si Pemburu pun datang ke istana dengan perasaan bimbang, karena ia sangat sayang pada si Parkit.";
		narasiSumatera[18]="Sampai di hadapan Raja Aceh, ia tidak bersedia memberikan si Parkit yang bersuara merdu itu kepada Sang Raja.";
		narasiSumatera[19]="Sang Raja sangat senang mendengar jawaban si Pemburu.";
		narasiSumatera[20]="Ia pun segera memerintahkan beberapa pengawalnya untuk mengambil burung parakeet tersebut dan menyerahkan uang yang dijanjikannya kepada si Pemburu.";
		narasiSumatera[21]="Si Parkit pun dibawa ke istana dan dimasukkan ke dalam sangkar emas." +
				"Setiap hari si Parkit disediakan makanan yang enak.";
		
		narasiSumatera[22]="Meskipun semuanya serba enak, namun si Parkit tetap tidak senang, karena ia merasa terpenjara.";
		narasiSumatera[23]="Ia ingin kembali ke hutan belantara tempat tinggalnya dulu, agar ia bisa terbang bebas bersama rakyatnya.";
		narasiSumatera[24]="Karena merasa sedih, si Parkit sudah beberapa hari tidak mau menyanyi untuk sang Raja.";
		narasiSumatera[25]="Mengetahui burung parakeetnya tidak mau menyanyi lagi, sang Raja mulai bimbang memikirkan burung parakeetnya.";
		narasiSumatera[26]="Karena ingin tahu keadaan burung itu yang sebenarnya, maka sang Raja pun memanggil petugas istana.";
		
		narasiSumatera[27]="Beberapa hari kemudian, si Parkit bahkan tidak mau memakan apa pun yang di sediakan di dalam sangkar emasnya." +
				"Ia terus teringat pada hutan belantara tempat tinggalnya dulu.";
		narasiSumatera[28]="Si Parkit pun mulai berpikir...";
		narasiSumatera[29]="Si Parkit tersenyum sambil membayangkan dirinya lepas dan terbang tinggi.";
		narasiSumatera[30]="Akhirnya, pada suatu hari, ia pun berpura-pura mati.";
		narasiSumatera[31]="Petugas Istana yang mengetahui si Parkit mati segera menghadap sang Raja.";
		
		narasiSumatera[32]="Sang Raja sangat sedih mendengar berita kematian burung parakeetnya, sebab tidak akan ada lagi yang menghiburnya.";
		narasiSumatera[33]="Meskipun sang Raja masih memiliki burung parakeet yang lain, tetapi suaranya tidak semerdu si Parkit." +
				"Karena si Parkit tidak bisa tertolong lagi, maka..";
		narasiSumatera[34]="Penguburan si Parkit akan dilaksanakan dengan upacara kebesaran kerajaan. " +
				"Pada saat persiapan penguburan, si Parkit dikeluarkan dari sangkarnya karena dianggap sudah mati.";
		narasiSumatera[35]="Ketika ia melihat semua orang sibuk, dengan cepatnya ia terbang setinggi-tingginya. " +
				"Di udara ia berteriak dengan riang gembira";
		narasiSumatera[36]="Orang-orang hanya terheran-heran melihat si Parkit yang dikira sudah mati itu bisa terbang tinggi.";
		
		narasiSumatera[37]="Akhirnya si Parkit yang cerdik itu bebas terbang ke hutan belantara tempat tinggalnya dulu yang ia cintai.";
		narasiSumatera[38]="Kedatangan si Parkit pun disambut dengan meriah oleh rakyatnya.";
		narasiSumatera[39]="Akhirnya, Si Parkit, Raja Parakeet, kembali tempat tinggalnya.";

		return narasiSumatera;
	}
	
}
