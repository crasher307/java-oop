package lesson6.homework;

import java.util.ArrayList;
import java.util.List;

class Controller<T> {
    private final List<? super T> data = new ArrayList<>();

    // public List<? super T> find(String field, Object value) {
    //     return this.data.stream().filter(model -> {
    //         try {
    //             return model.getClass().getDeclaredField(field).get(model).equals(value);
    //         } catch (NoSuchFieldException | IllegalAccessException ignored) {}
    //         return false;
    //     }).toList();
    // }

    public List<? super T> get() {
        return this.data;
    }

    public T add(T obj) {
        this.data.add(obj);
        return obj;
    }

    public void remove(T obj) {
        this.data.remove(obj);
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", this.getClass().getSimpleName(), this.data);
    }
}
