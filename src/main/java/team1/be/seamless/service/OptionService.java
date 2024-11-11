package team1.be.seamless.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team1.be.seamless.dto.OptionDTO.OptionCreate;
import team1.be.seamless.dto.OptionDTO.OptionDetail;
import team1.be.seamless.dto.OptionDTO.OptionSimple;
import team1.be.seamless.dto.OptionDTO.OptionUpdate;
import team1.be.seamless.dto.OptionDTO.getList;
import team1.be.seamless.entity.OptionEntity;
import team1.be.seamless.entity.enums.Role;
import team1.be.seamless.mapper.OptionMapper;
import team1.be.seamless.repository.OptionRepository;
import team1.be.seamless.util.errorException.BaseHandler;

@Service
public class OptionService {

    private OptionRepository optionRepository;

    private OptionMapper optionMapper;

    @Autowired
    public OptionService(OptionRepository optionRepository, OptionMapper optionMapper) {
        this.optionRepository = optionRepository;
        this.optionMapper = optionMapper;
    }

    public Page<OptionSimple> getProjectOptionList(getList param, String role) {
        authRole(role);

        return optionRepository.findAllByIsDeletedFalse(param.toPageable())
            .map(optionMapper::toSimple);
    }

    public OptionDetail getOption(Long id, String role) {
        authRole(role);

        return optionMapper.toDetail(optionRepository.findByIdAndIsDeletedFalse(id)
            .orElseThrow(() -> new BaseHandler(HttpStatus.NOT_FOUND, "해당하는 옵션이 존재하지 않습니다.")));
    }

    @Transactional
    public OptionDetail createOption(OptionCreate create, String role) {
        authRole(role);

        OptionEntity optionEntity = optionMapper.toEntity(create);
        optionRepository.save(optionEntity);

        return optionMapper.toDetail(optionEntity);
    }

    @Transactional
    public OptionDetail updateOption(Long id, OptionUpdate update, String role) {
        authRole(role);

        OptionEntity option = optionRepository.findByIdAndIsDeletedFalse(id)
            .orElseThrow(() -> new BaseHandler(HttpStatus.NOT_FOUND, "해당하는 옵션이 존재하지 않습니다."));

        optionMapper.toUpdate(option, update);

        return optionMapper.toDetail(option);
    }

    @Transactional
    public OptionDetail deleteOption(Long id, String role) {
        authRole(role);

        OptionEntity option = optionRepository.findByIdAndIsDeletedFalse(id)
            .orElseThrow(() -> new BaseHandler(HttpStatus.NOT_FOUND, "해당하는 옵션이 존재하지 않습니다."));

        option.setIsDeleted(true);

        return optionMapper.toDetail(option);
    }

    /**
     * 테스트용
     */
    @Profile("test")
    @Transactional
    public OptionEntity createOption(OptionCreate create) {
        return optionRepository.save(optionMapper.toEntity(create));
    }

    private void authRole(String role) {
        if(!Role.USER.isRole(role)) {
            throw new BaseHandler(HttpStatus.FORBIDDEN, "로그인한 유저만 조회 가능합니다.");
        }
    }

}
