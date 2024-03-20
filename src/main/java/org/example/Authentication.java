package org.example;

class Authentication {
    private IUserRepository userRepository;

    public Authentication(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String login, String password) {
        User user = userRepository.getUser(login);
        if (user != null) {
            return user.getPassword().equals(Hasher.hashPassword(password));
        }
        return false;
    }
}