package team404.project.repository.interfaces;

import team404.project.model.ConfirmCode;

public interface ConfirmCodeRepository extends CrudRepository<ConfirmCode, Integer> {
    ConfirmCode getByCode(String code);
}
