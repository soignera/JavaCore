package lesson24.touragency.common.business.domain;

public abstract class BaseDomain<ID> {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
