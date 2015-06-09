/*
 * The Qubes OS Project, http://www.qubes-os.org
 *
 * Copyright (C) 2010  Rafal Wojtczuk  <rafal@invisiblethingslab.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

#ifndef _LIBVCHAN_H
#define _LIBVCHAN_H

#include <stdint.h>
#include <stddef.h>
typedef int EVTCHN;

struct libvchan;
typedef struct libvchan libvchan_t;

libvchan_t *libvchan_server_init(int domain, int port, size_t read_min, size_t write_min);
libvchan_t *libvchan_client_init(int domain, int port);

int libvchan_write(libvchan_t *ctrl, const void *data, size_t size);
int libvchan_send(libvchan_t *ctrl, const void *data, size_t size);
int libvchan_read(libvchan_t *ctrl, void *data, size_t size);
int libvchan_recv(libvchan_t *ctrl, void *data, size_t size);
int libvchan_wait(libvchan_t *ctrl);
void libvchan_close(libvchan_t *ctrl);
EVTCHN libvchan_fd_for_select(libvchan_t *ctrl);
int libvchan_is_open(libvchan_t *ctrl);

int libvchan_data_ready(libvchan_t *ctrl);
int libvchan_buffer_space(libvchan_t *ctrl);

#endif /* _LIBVCHAN_H */
