package lesson10.touragency.common.business.search;

public abstract class BaseSearchCondition {
    protected Long id;
    protected SortType sortType;


    public SortType getSortType(){
         return sortType;}
    public void setSortType(SortType sortType){
         this.sortType= sortType;}

    public boolean needOrdering() {
        return  sortType != null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
