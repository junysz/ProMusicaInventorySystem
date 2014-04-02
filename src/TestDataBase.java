import java.sql.SQLException;

import com.group8.model.Testing_Data_Base_Class;



public class TestDataBase {

	
	public static void main(String[] args) {
		
		System.out.println("Testing Data Base connection");
		Testing_Data_Base_Class db = new Testing_Data_Base_Class();
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			db.save();
			System.out.println("saving");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		db.initializeCBox();
		
		db.disconnect();
	}

}
