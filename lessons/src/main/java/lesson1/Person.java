package lesson1;

public class Person {

    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    private Person(){}

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {

        private final Person person;

        public Builder() {
            this.person = new Person();
        }

        public Builder withFirstName(String firstName) {
            this.person.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.person.lastName = lastName;
            return this;
        }

        public Builder withMiddleName(String middleName) {
            this.person.middleName = middleName;
            return this;
        }
        public Builder withCountry(String country) {
            this.person.country = country;
            return this;
        }
        public Builder withAddress(String address) {
            this.person.address = address;
            return this;
        }
        public Builder withPhone(String phone) {
            this.person.phone = phone;
            return this;
        }
        public Builder withAge(int age) {
            this.person.age = age;
            return this;
        }
        public Builder withGender(String gender) {
            this.person.gender = gender;
            return this;
        }
        public Person build() {
            return this.person;
        }

    }
}
