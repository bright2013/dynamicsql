import org.junit.Test;

public class StringTest {

    @Test
    public void StringTest() {
        String a = "111";
        System.out.println(a.hashCode());
        a = a + "3";
        System.out.println(a.hashCode());

        StringBuilder st = new StringBuilder();
        st.append("111");
        System.out.println(st.hashCode());
        st.append("3");
        System.out.println(st.hashCode());

        StringBuffer sb = new StringBuffer();
        sb.append("111");
        System.out.println(sb.hashCode());
        sb.append("3");
        System.out.println(sb.hashCode());

    }
}
