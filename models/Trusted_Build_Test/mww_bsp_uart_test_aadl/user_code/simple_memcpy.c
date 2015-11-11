
void
memcpy(uint8_t *dst, const uint8_t *src, const uint8_t length)
{
    uint8_t *const dst_end = dst + length;

    while (dst < dst_end)
    {
        *dst++ = *src++;
    }
}
