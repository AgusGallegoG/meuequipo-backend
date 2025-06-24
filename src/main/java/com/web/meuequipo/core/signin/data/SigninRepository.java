package com.web.meuequipo.core.signin.data;

import com.web.meuequipo.core.signin.Signin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SigninRepository extends JpaRepository<Signin, Long> {

}
