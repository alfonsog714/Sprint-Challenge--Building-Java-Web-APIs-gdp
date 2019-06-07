package local.alfonso.gdp;

import local.alfonso.gdp.model.GDP;

import java.util.Comparator;

public class GDPComp implements Comparator<GDP>{

    @Override
    public int compare(GDP o1, GDP o2) {
        return Long.compare(o1.getGdp(), o2.getGdp());
    }
}
