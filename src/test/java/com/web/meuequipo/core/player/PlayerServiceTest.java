package com.web.meuequipo.core.player;

import com.web.meuequipo.core.category.Category;
import com.web.meuequipo.core.category.data.CategoryRepository;
import com.web.meuequipo.core.player.data.PlayerRepository;
import com.web.meuequipo.core.player.dto.request.PlayerRequest;
import com.web.meuequipo.core.player.dto.response.PlayerResponse;
import com.web.meuequipo.core.player.service.PlayerServiceImpl;
import com.web.meuequipo.core.season.Season;
import com.web.meuequipo.core.season.data.SeasonRepository;
import com.web.meuequipo.core.season.exception.SeasonException;
import com.web.meuequipo.core.shared.enums.Sex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private SeasonRepository seasonRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    void savePlayerTest_create() {
        Long categoryId = 1L;
        PlayerRequest request = new PlayerRequest();
        request.setId(null); // Id = null -> Create
        request.setName("Agustin");
        request.setSurnames("Gallego Gómez");
        request.setSex(Sex.MALE.getId());
        request.setBirthDate(LocalDate.of(2001, 6, 20));
        request.setCategory(categoryId);

        Season season = new Season();
        season.setId(1L);
        season.setIsActive(true);

        Category category = new Category();
        category.setId(categoryId);
        category.setName("Senior");

        Player result = new Player();
        result.setId(1L);
        result.setName("Agustin");
        result.setSurnames("Gallego Gómez");
        result.setPlayerSex(Sex.MALE.getId());
        result.setBirthDate(LocalDate.of(2001, 6, 20));
        result.setCategory(category);
        result.setSeason(season);

        when(categoryRepository.findCategoryByIdAndIsActiveTrueOfActualSeason(categoryId))
                .thenReturn(Optional.of(category));
        when(seasonRepository.findByIsActiveTrue())
                .thenReturn(Optional.of(season));
        when(playerRepository.save(any()))
                .thenReturn(result);

        PlayerResponse response = playerService.savePlayer(request);

        assertNotNull(response);
        assertEquals(response.getId(), result.getId());
        verify(playerRepository, times(1)).save(any());
    }

    @Test
    void savePlayerTest_update() {
        Long categoryId = 1L;
        PlayerRequest request = new PlayerRequest();
        request.setId(1L); // Id = null -> Create
        request.setName("Agustin");
        request.setSurnames("Gallego Gómez");
        request.setSex(Sex.MALE.getId());
        request.setBirthDate(LocalDate.of(2001, 6, 20));
        request.setCategory(categoryId);

        Season season = new Season();
        season.setId(1L);
        season.setIsActive(true);

        Category category = new Category();
        category.setId(categoryId);
        category.setName("Senior");

        Player result = new Player();
        result.setId(1L);
        result.setName("Agustin");
        result.setSurnames("Gallego Gómez");
        result.setPlayerSex(Sex.MALE.getId());
        result.setBirthDate(LocalDate.of(2001, 6, 20));
        result.setCategory(category);
        result.setSeason(season);

        when(categoryRepository.findCategoryByIdAndIsActiveTrueOfActualSeason(categoryId))
                .thenReturn(Optional.of(category));
        when(playerRepository.findPlayerByIdOfActualSeason(1L))
                .thenReturn(Optional.of(result));
        when(playerRepository.save(any()))
                .thenReturn(result);

        PlayerResponse response = playerService.savePlayer(request);

        assertNotNull(response);
        assertEquals(response.getId(), result.getId());
        verify(playerRepository, times(1)).save(any());
    }

    @Test
    void savePlayer_error_notSeason() {
        PlayerRequest request = new PlayerRequest();
        request.setId(null); // Id = null -> Create
        request.setName("Agustin");
        request.setSurnames("Gallego Gómez");
        request.setSex(Sex.MALE.getId());
        request.setBirthDate(LocalDate.of(2001, 6, 20));
        request.setCategory(1L);

        Category category = new Category();
        category.setId(1L);
        category.setName("Senior");

        when(seasonRepository.findByIsActiveTrue())
                .thenReturn(Optional.empty());
        when(categoryRepository.findCategoryByIdAndIsActiveTrueOfActualSeason(any()))
                .thenReturn(Optional.of(category));

        SeasonException exception = assertThrows(SeasonException.class, () -> playerService.savePlayer(request));

        assertTrue(exception.getMessage().contains("Non se atopou Season"));
    }
}
