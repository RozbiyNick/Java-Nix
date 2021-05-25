package debates.test;


import debates.dao.DaoFactory;
import debates.dao.jdbc.JdbcArgumentDao;
import debates.model.Argument;
import debates.model.status.ArgumentStatus;
import lombok.SneakyThrows;

public class Test {
    
    @SneakyThrows
	public static void main(String[] args) {
		Argument arg = Argument.builder().text("Yes!").status(ArgumentStatus.True).parent(null).build();
		try{
			System.out.println("Test find arg");
			JdbcArgumentDao argDao = (JdbcArgumentDao) DaoFactory.getInstance("jdbc", "src/main/resources/db.properties").getArgumentDao();
			arg = argDao.findById(3l);
			System.out.println(arg.toString());
		}
		finally {
			
		}
		
	}
}
