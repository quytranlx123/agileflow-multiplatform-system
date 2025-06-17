export function showLoadingFor1s() {
    const loading = document.getElementById('global-loading');
    if (!loading) return;

    loading.style.display = 'flex';
    setTimeout(() => {
        loading.style.display = 'none';
    }, 1000); // 1000 ms = 1 giây
}
