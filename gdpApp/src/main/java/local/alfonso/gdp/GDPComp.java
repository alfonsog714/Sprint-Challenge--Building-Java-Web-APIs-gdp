package local.alfonso.gdp;

import local.alfonso.gdp.model.GDP;

import java.util.Comparator;

public class GDPComp implements Comparator<GDP>{

    @Override
    public int compare(GDP o1, GDP o2) {
        return Long.compare(o2.getGdp(), o1.getGdp());
    }
}
