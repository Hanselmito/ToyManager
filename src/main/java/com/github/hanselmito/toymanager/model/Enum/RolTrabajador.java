package com.github.hanselmito.toymanager.model.Enum;

public enum RolTrabajador {
    admin("admin"),
    empleado("empleado");

    private String rol;

    RolTrabajador(String rol) {
        this.rol = rol;
    }

    public static RolTrabajador fromString(String rol) {
        for (RolTrabajador r : RolTrabajador.values()) {
            if (r.rol.equalsIgnoreCase(rol)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No enum constant " + RolTrabajador.class.getCanonicalName() + "." + rol);
    }
}
