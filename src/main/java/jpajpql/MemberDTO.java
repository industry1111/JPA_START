package jpajpql;


public class MemberDTO {

        private String username;
        private int age;

        public MemberDTO(String username, int age) {
            this.username = username;
            this.age = age;
        }

        public MemberDTO() {
        }

        public String getUsername() {
            return username;
        }

        public int getAge() {
            return age;
        }
}
