import com.api.Cmd;
import com.google.gson.Gson;

public class TestGson {
	public static void main(String[] args) {

		Gson gson = new Gson();
		String bS = "{   \"body\": {       \"errno\": 0,       \"error\": \"success\",       \"data\":{          \"content\":\"12345\",          \"sender\": \"12344@aa.com\"        }    },   \"cmd\": \"resp.email.send\",    \"sign\": \"51BAA29E9CE298241F52985864D23165\",    \"source\": \"65400\",     \"secret\": \"123456788\",   \"ticket\": \"FEBCA99A-967D-EBDC-8588-F530B3E235E7\",    \"timestamp\": 1452686921,    \"version\": 3}";
		Cmd user = gson.fromJson(bS, Cmd.class);
		System.out.println(user);

	}
}
