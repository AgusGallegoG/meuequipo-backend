package com.web.meuequipo.core.shared.rest;

import com.web.meuequipo.core.category.exception.CategoryException;
import com.web.meuequipo.core.game.exception.GameException;
import com.web.meuequipo.core.image.exception.ImageException;
import com.web.meuequipo.core.player.exception.PlayerException;
import com.web.meuequipo.core.publication.exception.PublicationException;
import com.web.meuequipo.core.rival.exception.RivalException;
import com.web.meuequipo.core.season.exception.SeasonException;
import com.web.meuequipo.core.shared.exception.BaseException;
import com.web.meuequipo.core.signinPeriod.exception.SigninFormException;
import com.web.meuequipo.core.sponsor.exception.SponsorException;
import com.web.meuequipo.core.team.exception.TeamException;
import com.web.meuequipo.core.user.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> handleBaseException(BaseException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(SeasonException.class)
    public ResponseEntity<Object> handleSeasonException(SeasonException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleUserException(UserException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<Object> handleCategoryException(CategoryException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ImageException.class)
    public ResponseEntity<Object> handleImageException(ImageException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SponsorException.class)
    public ResponseEntity<Object> handleSponsorException(SponsorException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RivalException.class)
    public ResponseEntity<Object> handleRivalException(RivalException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TeamException.class)
    public ResponseEntity<Object> handleTeamException(TeamException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(GameException.class)
    public ResponseEntity<Object> handleGameException(GameException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PublicationException.class)
    public ResponseEntity<Object> handlePublicationException(PublicationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PlayerException.class)
    public ResponseEntity<Object> handlePlayerException(PlayerException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SigninFormException.class)
    public ResponseEntity<Object> handleSigninFormException(SigninFormException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
