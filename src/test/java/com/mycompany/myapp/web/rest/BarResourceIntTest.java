package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ParisByNightApp;

import com.mycompany.myapp.domain.Bar;
import com.mycompany.myapp.repository.BarRepository;
import com.mycompany.myapp.service.BarService;
import com.mycompany.myapp.service.dto.BarDTO;
import com.mycompany.myapp.service.mapper.BarMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BarResource REST controller.
 *
 * @see BarResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParisByNightApp.class)
public class BarResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_START_DATE = "AAAAAAAAAA";
    private static final String UPDATED_START_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_END_DATE = "AAAAAAAAAA";
    private static final String UPDATED_END_DATE = "BBBBBBBBBB";

    private static final Double DEFAULT_COVER_CHARGE = 1D;
    private static final Double UPDATED_COVER_CHARGE = 2D;

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIAL_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_SPECIAL_EVENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_FROM_4_TO_6 = 1;
    private static final Integer UPDATED_FROM_4_TO_6 = 2;

    private static final Integer DEFAULT_FROM_6_TO_8 = 1;
    private static final Integer UPDATED_FROM_6_TO_8 = 2;

    private static final Integer DEFAULT_FROM_8_TO_10 = 1;
    private static final Integer UPDATED_FROM_8_TO_10 = 2;

    private static final Integer DEFAULT_FROM_10_TO_MID = 1;
    private static final Integer UPDATED_FROM_10_TO_MID = 2;

    private static final Integer DEFAULT_FROM_MID_TO_2 = 1;
    private static final Integer UPDATED_FROM_MID_TO_2 = 2;

    private static final Integer DEFAULT_FROM_2_TO_4 = 1;
    private static final Integer UPDATED_FROM_2_TO_4 = 2;

    @Autowired
    private BarRepository barRepository;

    @Autowired
    private BarMapper barMapper;

    @Autowired
    private BarService barService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBarMockMvc;

    private Bar bar;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BarResource barResource = new BarResource(barService);
        this.restBarMockMvc = MockMvcBuilders.standaloneSetup(barResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bar createEntity(EntityManager em) {
        Bar bar = new Bar()
            .name(DEFAULT_NAME)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .coverCharge(DEFAULT_COVER_CHARGE)
            .adresse(DEFAULT_ADRESSE)
            .type(DEFAULT_TYPE)
            .specialEvent(DEFAULT_SPECIAL_EVENT)
            .from4To6(DEFAULT_FROM_4_TO_6)
            .from6To8(DEFAULT_FROM_6_TO_8)
            .from8To10(DEFAULT_FROM_8_TO_10)
            .from10ToMid(DEFAULT_FROM_10_TO_MID)
            .fromMidTo2(DEFAULT_FROM_MID_TO_2)
            .from2To4(DEFAULT_FROM_2_TO_4);
        return bar;
    }

    @Before
    public void initTest() {
        bar = createEntity(em);
    }

    @Test
    @Transactional
    public void createBar() throws Exception {
        int databaseSizeBeforeCreate = barRepository.findAll().size();

        // Create the Bar
        BarDTO barDTO = barMapper.toDto(bar);
        restBarMockMvc.perform(post("/api/bars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(barDTO)))
            .andExpect(status().isCreated());

        // Validate the Bar in the database
        List<Bar> barList = barRepository.findAll();
        assertThat(barList).hasSize(databaseSizeBeforeCreate + 1);
        Bar testBar = barList.get(barList.size() - 1);
        assertThat(testBar.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBar.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testBar.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testBar.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBar.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testBar.getCoverCharge()).isEqualTo(DEFAULT_COVER_CHARGE);
        assertThat(testBar.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testBar.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testBar.getSpecialEvent()).isEqualTo(DEFAULT_SPECIAL_EVENT);
        assertThat(testBar.getFrom4To6()).isEqualTo(DEFAULT_FROM_4_TO_6);
        assertThat(testBar.getFrom6To8()).isEqualTo(DEFAULT_FROM_6_TO_8);
        assertThat(testBar.getFrom8To10()).isEqualTo(DEFAULT_FROM_8_TO_10);
        assertThat(testBar.getFrom10ToMid()).isEqualTo(DEFAULT_FROM_10_TO_MID);
        assertThat(testBar.getFromMidTo2()).isEqualTo(DEFAULT_FROM_MID_TO_2);
        assertThat(testBar.getFrom2To4()).isEqualTo(DEFAULT_FROM_2_TO_4);
    }

    @Test
    @Transactional
    public void createBarWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = barRepository.findAll().size();

        // Create the Bar with an existing ID
        bar.setId(1L);
        BarDTO barDTO = barMapper.toDto(bar);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBarMockMvc.perform(post("/api/bars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(barDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bar in the database
        List<Bar> barList = barRepository.findAll();
        assertThat(barList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBars() throws Exception {
        // Initialize the database
        barRepository.saveAndFlush(bar);

        // Get all the barList
        restBarMockMvc.perform(get("/api/bars?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bar.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].coverCharge").value(hasItem(DEFAULT_COVER_CHARGE.doubleValue())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].specialEvent").value(hasItem(DEFAULT_SPECIAL_EVENT.toString())))
            .andExpect(jsonPath("$.[*].from4To6").value(hasItem(DEFAULT_FROM_4_TO_6)))
            .andExpect(jsonPath("$.[*].from6To8").value(hasItem(DEFAULT_FROM_6_TO_8)))
            .andExpect(jsonPath("$.[*].from8To10").value(hasItem(DEFAULT_FROM_8_TO_10)))
            .andExpect(jsonPath("$.[*].from10ToMid").value(hasItem(DEFAULT_FROM_10_TO_MID)))
            .andExpect(jsonPath("$.[*].fromMidTo2").value(hasItem(DEFAULT_FROM_MID_TO_2)))
            .andExpect(jsonPath("$.[*].from2To4").value(hasItem(DEFAULT_FROM_2_TO_4)));
    }

    @Test
    @Transactional
    public void getBar() throws Exception {
        // Initialize the database
        barRepository.saveAndFlush(bar);

        // Get the bar
        restBarMockMvc.perform(get("/api/bars/{id}", bar.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bar.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.coverCharge").value(DEFAULT_COVER_CHARGE.doubleValue()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.specialEvent").value(DEFAULT_SPECIAL_EVENT.toString()))
            .andExpect(jsonPath("$.from4To6").value(DEFAULT_FROM_4_TO_6))
            .andExpect(jsonPath("$.from6To8").value(DEFAULT_FROM_6_TO_8))
            .andExpect(jsonPath("$.from8To10").value(DEFAULT_FROM_8_TO_10))
            .andExpect(jsonPath("$.from10ToMid").value(DEFAULT_FROM_10_TO_MID))
            .andExpect(jsonPath("$.fromMidTo2").value(DEFAULT_FROM_MID_TO_2))
            .andExpect(jsonPath("$.from2To4").value(DEFAULT_FROM_2_TO_4));
    }

    @Test
    @Transactional
    public void getNonExistingBar() throws Exception {
        // Get the bar
        restBarMockMvc.perform(get("/api/bars/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBar() throws Exception {
        // Initialize the database
        barRepository.saveAndFlush(bar);
        int databaseSizeBeforeUpdate = barRepository.findAll().size();

        // Update the bar
        Bar updatedBar = barRepository.findOne(bar.getId());
        updatedBar
            .name(UPDATED_NAME)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .coverCharge(UPDATED_COVER_CHARGE)
            .adresse(UPDATED_ADRESSE)
            .type(UPDATED_TYPE)
            .specialEvent(UPDATED_SPECIAL_EVENT)
            .from4To6(UPDATED_FROM_4_TO_6)
            .from6To8(UPDATED_FROM_6_TO_8)
            .from8To10(UPDATED_FROM_8_TO_10)
            .from10ToMid(UPDATED_FROM_10_TO_MID)
            .fromMidTo2(UPDATED_FROM_MID_TO_2)
            .from2To4(UPDATED_FROM_2_TO_4);
        BarDTO barDTO = barMapper.toDto(updatedBar);

        restBarMockMvc.perform(put("/api/bars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(barDTO)))
            .andExpect(status().isOk());

        // Validate the Bar in the database
        List<Bar> barList = barRepository.findAll();
        assertThat(barList).hasSize(databaseSizeBeforeUpdate);
        Bar testBar = barList.get(barList.size() - 1);
        assertThat(testBar.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBar.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testBar.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testBar.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBar.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testBar.getCoverCharge()).isEqualTo(UPDATED_COVER_CHARGE);
        assertThat(testBar.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testBar.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testBar.getSpecialEvent()).isEqualTo(UPDATED_SPECIAL_EVENT);
        assertThat(testBar.getFrom4To6()).isEqualTo(UPDATED_FROM_4_TO_6);
        assertThat(testBar.getFrom6To8()).isEqualTo(UPDATED_FROM_6_TO_8);
        assertThat(testBar.getFrom8To10()).isEqualTo(UPDATED_FROM_8_TO_10);
        assertThat(testBar.getFrom10ToMid()).isEqualTo(UPDATED_FROM_10_TO_MID);
        assertThat(testBar.getFromMidTo2()).isEqualTo(UPDATED_FROM_MID_TO_2);
        assertThat(testBar.getFrom2To4()).isEqualTo(UPDATED_FROM_2_TO_4);
    }

    @Test
    @Transactional
    public void updateNonExistingBar() throws Exception {
        int databaseSizeBeforeUpdate = barRepository.findAll().size();

        // Create the Bar
        BarDTO barDTO = barMapper.toDto(bar);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBarMockMvc.perform(put("/api/bars")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(barDTO)))
            .andExpect(status().isCreated());

        // Validate the Bar in the database
        List<Bar> barList = barRepository.findAll();
        assertThat(barList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBar() throws Exception {
        // Initialize the database
        barRepository.saveAndFlush(bar);
        int databaseSizeBeforeDelete = barRepository.findAll().size();

        // Get the bar
        restBarMockMvc.perform(delete("/api/bars/{id}", bar.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Bar> barList = barRepository.findAll();
        assertThat(barList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bar.class);
        Bar bar1 = new Bar();
        bar1.setId(1L);
        Bar bar2 = new Bar();
        bar2.setId(bar1.getId());
        assertThat(bar1).isEqualTo(bar2);
        bar2.setId(2L);
        assertThat(bar1).isNotEqualTo(bar2);
        bar1.setId(null);
        assertThat(bar1).isNotEqualTo(bar2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BarDTO.class);
        BarDTO barDTO1 = new BarDTO();
        barDTO1.setId(1L);
        BarDTO barDTO2 = new BarDTO();
        assertThat(barDTO1).isNotEqualTo(barDTO2);
        barDTO2.setId(barDTO1.getId());
        assertThat(barDTO1).isEqualTo(barDTO2);
        barDTO2.setId(2L);
        assertThat(barDTO1).isNotEqualTo(barDTO2);
        barDTO1.setId(null);
        assertThat(barDTO1).isNotEqualTo(barDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(barMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(barMapper.fromId(null)).isNull();
    }
}
