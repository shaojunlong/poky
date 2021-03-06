SUMMARY = "GTK+2 standard themes"
HOMEPAGE = "http://ftp.gnome.org/pub/GNOME/sources/gnome-themes-standard/"
BUGTRACKER = "https://bugzilla.gnome.org/"
SECTION = "x11/gnome"

LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

inherit autotools pkgconfig gettext gtk-icon-cache upstream-version-is-even

DEPENDS += "intltool-native gtk+"

MAJ_VER = "${@oe.utils.trim_version("${PV}", 2)}"
SRC_URI = "${GNOME_MIRROR}/${BPN}/${MAJ_VER}/${BPN}-${PV}.tar.xz \
          "

SRC_URI[md5sum] = "4d17bc62e4d0c5440fc4eda3d9271367"
SRC_URI[sha256sum] = "e646eb04c225282b7df7fff65741adaad4cf9ed2c12616b7310e7edd27d2bacb"

EXTRA_OECONF = "--disable-gtk3-engine"

do_install_append() {
        # Only building Adwaita, remove highcontrast files
        rm -rf ${D}${prefix}/share/themes/HighContrast \
               ${D}${prefix}/share/icons
}

# There could be gnome-theme-highcontrast as well but that requires
# gtk+3 and includes lots of icons (is also broken with B != S).
PACKAGES += "gnome-theme-adwaita \
             gnome-theme-adwaita-dbg \
             gnome-theme-adwaita-dev"

FILES_gnome-theme-adwaita = "${prefix}/share/themes/Adwaita \
                              ${libdir}/gtk-2.0/2.10.0/engines/libadwaita.so"
FILES_gnome-theme-adwaita-dev = "${libdir}/gtk-2.0/2.10.0/engines/libadwaita.la"
FILES_gnome-theme-adwaita-dbg = "${libdir}/gtk-2.0/2.10.0/engines/.debug/libadwaita.so"
